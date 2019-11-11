package com.example.demo.RabbotMQ.config;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @program: ProjectCollection
 * @description: 处理成功失败消息，失败回滚
 * @author: xiuchao Song
 * @create: 2019-11-01 14:45
 **/
@Slf4j
@Configuration
public class SignMessageConfig {

    @Autowired
    private CachingConnectionFactory factory;

    /**************************************************
     * RabbitMQ服务器连接配置
     */
    @Value("${spring.rabbitmq.host}")
    private String address;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;


    /**
     * 获取连接
     * @return ConnectionFactory
     */
    //@Bean
    public ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address + ":" + port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        // 如果要进行消息回调，则这里必须设置为true
        connectionFactory.setPublisherConfirms(publisherConfirms);
        return connectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate() throws IOException {
        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        //每个rabbitTemplate只能有一个confirm-callback和return-callback，
        // 如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback
        //使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
        factory.setPublisherConfirms(true);
        factory.setPublisherReturns(true);


        //设置队列持久化
        Connection connection = factory.createConnection();
        Channel channel = connection.createChannel(false);
        channel.queueDeclare(QueueConstant.TopicFanoutA, true, false, false, null);
        //带进源码去看，第三个参数是路由key
        channel.queueBind(QueueConstant.TopicFanoutA,QueueConstant.TopicExchange,"");
         //保证一次只分发一个
        channel.basicQos(1);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setEncoding("UTF-8");
//        /**
//         * 如果消息没有到exchange,则confirm回调,ack=false
//         * 如果消息到达exchange,则confirm回调,ack=true
//         * exchange到queue成功,则不回调return
//         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
//         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if(ack){
                    log.info("消息发送成功======>:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }else{
                    log.info("消息发送失败======>:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                String msgId = "";
                if (message.getMessageProperties().getCorrelationId() != null) {
                    msgId =message.getMessageProperties().getCorrelationId();
                }
                log.info("消息丢失======>:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
                //System.out.println("msgId====>"+msgId);
            }
        });
        return rabbitTemplate;
    }


    /**
     * 配置RabbitAdmin
	 * <p>
	 * 	RabbitAdmin可以帮助我们自动创建交换机、队列
	 * </p>
     * @param defaultConnectionFactory
	 * @return RabbitAdmin
	 */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory defaultConnectionFactory){
        return new RabbitAdmin(defaultConnectionFactory);
    }

    /**
     * 生产Jackson消息转换器
     * @return Jackson2JsonMessageConverter
     */
    /**
     * Jackson2JsonMessageConverter extends AbstractJackson2MessageConverter
     *  https://www.jianshu.com/p/83861676051c
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}

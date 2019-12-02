package com.example.demo.RabbotMQ.config;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: ProjectCollection
 * @description: RabbitMq配置信息
 * @author: xiuchao Song
 * @create: 2019-11-01 14:33
 **/
@Slf4j
//@Configuration
public class RabbitMqConfig {

    /**
     * 直连队列
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(QueueConstant.directQueue);
    }


    //===============以下是验证topic Exchange的队列==========
    // Bean默认的name是方法名
    @Bean(name="message")
    public Queue queueMessage() {
        return new Queue(QueueConstant.TopicQueueMessage);
    }
    @Bean(name="messages")
    public Queue queueMessages() {
        return new Queue(QueueConstant.TopicQueueMessages);
    }

    //===============以上是验证topic Exchange的队列==========

    //===============以下是验证Fanout Exchange的队列==========



    public Queue FanoutAMessage() {
        return new Queue(QueueConstant.TopicFanoutA);
    }
    public Queue FanoutBMessage() {
        return new Queue(QueueConstant.TopicFanoutB);
    }
    public Queue FanoutCMessage() {
        return new Queue(QueueConstant.TopicFanoutC);
    }
    //===============以上是验证Fanout Exchange的队列==========


    /**
     *     exchange是交换机交换机的主要作用是接收相应的消息并且绑定到指定的队列.交换机有四种类型,分别为Direct,topic,headers,Fanout.
     * 　　Direct是RabbitMQ默认的交换机模式,也是最简单的模式.即创建消息队列的时候,指定一个BindingKey.当发送者发送消息的时候,指定对应的Key.当Key和消息队列的BindingKey一致的时候,消息将会被发送到该消息队列中.
     * 　　topic转发信息主要是依据通配符,队列和交换机的绑定主要是依据一种模式(通配符+字符串),而当发送消息的时候,只有指定的Key和该模式相匹配的时候,消息才会被发送到该消息队列中.
     * 　　headers也是根据一个规则进行匹配,在消息队列和交换机绑定的时候会指定一组键值对规则,而发送消息的时候也会指定一组键值对规则,当两组键值对规则相匹配的时候,消息会被发送到匹配的消息队列中.
     * 　　Fanout是路由广播的形式,将会把消息发给绑定它的全部队列,即便设置了key,也会被忽略.
     */


    /**
     * 直连交换机
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(QueueConstant.DirectExchange);
    }
    /**
     * 直连交换机与队列绑定
     * @return
     */
    @Bean
    Binding bindingExchangeDirect(){
        return  BindingBuilder.bind(directQueue()).to(directExchange()).with("direct");
    }


    /**
     * 订阅模式交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        // 参数1为交换机的名称
        return new TopicExchange(QueueConstant.TopicExchange);
    }
    /**
     * 将队列topic.message与exchange绑定，routing_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    // 如果参数名和上面用到方法名称一样，可以不用写@Qualifier
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message")Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }
    /**
     * 将队列topic.messages与exchange绑定，routing_key为topic.#,模糊匹配
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages")Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
    }


    /**
     * //配置广播路由器
     * @return FanoutExchange
     */
    @Bean
    FanoutExchange fanoutExchange() {
        // 参数1为交换机的名称
        return new FanoutExchange(QueueConstant.FanoutExchange);
    }
    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(FanoutAMessage()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(FanoutBMessage()).to(fanoutExchange());
    }
    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(FanoutCMessage()).to(fanoutExchange());
    }

}

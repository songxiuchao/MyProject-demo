package com.example.demo.RabbotMQ.Fanout;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import com.example.demo.RabbotMQ.po.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 广播模式的生产者
 * @author: xiuchao Song
 * @create: 2019-11-02 08:09
 **/
@Component
public class FantoutSender {
    /**
     * AmqpTemplate可以说是RabbitTemplate父类，RabbitTemplate实现类RabbitOperations接口，RabbitOperations继承了AmqpTemplate接口
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;


    /**
     * 广播模式生产者
     */
    public void sender() {
        try {
            User user=new User("songxiuchao",22,"Qingdao");
            System.out.println("广播模式生产者发送的消息======》 : " + user);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType("application/json");
            Message message=new Message(json.getBytes(),messageProperties);
            rabbitTemplate.convertAndSend(QueueConstant.FanoutExchange,message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

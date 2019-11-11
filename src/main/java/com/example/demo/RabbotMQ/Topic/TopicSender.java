package com.example.demo.RabbotMQ.Topic;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.RabbotMQ.constant.QueueConstant;
import com.example.demo.RabbotMQ.po.User;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 订阅模式的生产者
 * @author: xiuchao Song
 * @create: 2019-11-02 07:55
 **/
@Component
@Slf4j
public class TopicSender {
    /**
     * AmqpTemplate可以说是RabbitTemplate父类，RabbitTemplate实现类RabbitOperations接口，RabbitOperations继承了AmqpTemplate接口
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 订阅模式生产者
     */
    public void sender(User user) {
        System.out.println("订阅模式生产者发送的消息======》 : " + user.toString());
        Gson gson=new Gson();
        gson.toJson(user);
            rabbitTemplate.convertAndSend(QueueConstant.TopicExchange,"topic.message", user.toString());
    }
    /**
     * 订阅模式生产者
     */
    public void senderMessages(User user) {
        try {
            System.out.println("订阅模式生产者发送的消息======》 : " + user);
           /* ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(user);
            System.out.println(json);
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType("application/json");
            Message message=new Message(json.getBytes(),messageProperties);*/
            Gson gson=new Gson();
            gson.toJson(user);
            rabbitTemplate.convertAndSend(QueueConstant.TopicExchange,"topic.#",user.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

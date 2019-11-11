package com.example.demo.RabbotMQ.Direct;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: ProjectCollection
 * @description: 生产者Sender
 * @author: xiuchao Song
 * @create: 2019-11-01 14:37
 **/
@Component
public class Sender {
    /**
     * AmqpTemplate可以说是RabbitTemplate父类，RabbitTemplate实现类RabbitOperations接口，RabbitOperations继承了AmqpTemplate接口
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 用于单生产者-》单消费者测试
     */
    public void send() {
        String sendMsg = "sender";
        System.out.println("单生产者多消费者发送的消息======》 : " + sendMsg);
        try {
            rabbitTemplate.convertAndSend(QueueConstant.DirectExchange,"direct", sendMsg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

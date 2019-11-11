package com.example.demo.RabbotMQ.Topic;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 订阅模式的消费者
 * @author: xiuchao Song
 * @create: 2019-11-02 08:03
 **/
@Component
public class TopicReceiver {

    @RabbitListener(queues = QueueConstant.TopicQueueMessage)
    public void process(String user) {
        System.out.println("topicQueueMessage==>" +user);
    }
}

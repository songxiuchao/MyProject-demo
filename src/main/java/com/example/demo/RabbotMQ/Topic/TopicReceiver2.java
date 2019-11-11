package com.example.demo.RabbotMQ.Topic;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import com.example.demo.RabbotMQ.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 订阅模式的消费者
 * @author: xiuchao Song
 * @create: 2019-11-02 08:10
 **/
@Component
@Slf4j
public class TopicReceiver2 {

    @RabbitListener(queues = QueueConstant.TopicQueueMessages)
    public void process(String user) {

        System.out.println("topicQueueMessages==>" + user);
    }

}

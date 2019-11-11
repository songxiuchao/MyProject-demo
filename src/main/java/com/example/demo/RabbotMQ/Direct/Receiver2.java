package com.example.demo.RabbotMQ.Direct;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: ProjectCollection
 * @description: 消费者Receiver
 * @author: xiuchao Song
 * @create: 2019-11-01 14:39
 **/
@Component
public class Receiver2 {

    @RabbitListener(queues = QueueConstant.directQueue)
    public void process(String directQueue) {
        System.out.println("directQueue2==>  " + directQueue);
    }

}

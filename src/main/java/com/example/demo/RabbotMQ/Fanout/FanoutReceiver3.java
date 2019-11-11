package com.example.demo.RabbotMQ.Fanout;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 广播模式的消费者3
 * @author: xiuchao Song
 * @create: 2019-11-02 09:05
 **/
@Component
public class FanoutReceiver3 {

    @RabbitListener(queues = QueueConstant.TopicFanoutC)
    public void process(String fanoutQueueC) {
        System.out.println("fanoutQueueC==>" + fanoutQueueC);
    }
}

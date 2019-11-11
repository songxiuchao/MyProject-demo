package com.example.demo.RabbotMQ.Fanout;

import com.example.demo.RabbotMQ.constant.QueueConstant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 广播模式的消费者2
 * @author: xiuchao Song
 * @create: 2019-11-02 09:05
 **/
@Component
public class FanoutReceiver2 {

    @RabbitListener(queues = QueueConstant.TopicFanoutB)
    public void process(String fanoutQueueB) {
        System.out.println("fanoutQueueB==>" + fanoutQueueB);
    }
}

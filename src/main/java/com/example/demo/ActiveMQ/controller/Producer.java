package com.example.demo.ActiveMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @program: demo
 * @description: 发送者测试类
 * @author: xiuchao Song
 * @create: 2019-11-16 10:44
 **/

@RestController
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    //发送queue类型消息
    @GetMapping("/queue")
    public void sendQueueMsg(String msg){
        jmsTemplate.convertAndSend(queue, msg);
    }

    //发送topic类型消息
    @GetMapping("/topic")
    public void sendTopicMsg(String msg){
        jmsTemplate.convertAndSend(topic, msg);
    }

}
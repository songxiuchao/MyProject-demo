package com.example.demo.RabbotMQ.controller;

import com.example.demo.RabbotMQ.Direct.Sender;
import com.example.demo.RabbotMQ.Fanout.FantoutSender;
import com.example.demo.RabbotMQ.Topic.TopicSender;
import com.example.demo.RabbotMQ.po.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: ProjectCollection
 * @description: 启动RabbitMq
 * @author: xiuchao Song
 * @create: 2019-11-01 15:21
 **/
@Slf4j
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private Sender sender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FantoutSender fantoutSender;
    /**
     * 测试直连模式
     */
    @PostMapping("/direct")
    public void DirectTest(){
        sender.send();
    }

    /**
     * 测试订阅模式
     */
    @PostMapping("/topic")
    public void TopicTest(User user){
        //User user=new User("songxiuchao",22,"Qingdao");
        topicSender.sender(user);
        topicSender.senderMessages(user);
    }

    /**
     * 测试广播模式
     */
    @PostMapping("/fanout")
    public void FanoutTest(){
        fantoutSender.sender();
    }

}

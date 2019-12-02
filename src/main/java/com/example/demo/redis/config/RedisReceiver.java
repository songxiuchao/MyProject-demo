package com.example.demo.redis.config;

import org.springframework.stereotype.Component;

/**
 * @program: demo
 * @description: 消息监听类
 * @author: xiuchao Song
 * @create: 2019-12-02 14:04
 **/
@Component
public class RedisReceiver {
    public void receiveMessage(String message) {
        // TODO 这里是收到通道的消息之后执行的方法
        System.out.println(message);
    }
}
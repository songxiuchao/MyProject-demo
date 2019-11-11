package com.example.demo.Kafka.controller;

import com.example.demo.Kafka.config.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: kafka的测试类
 * @author: xiuchao Song
 * @create: 2019-11-04 10:54
 **/
@RestController
@RequestMapping("/kafka")
@Slf4j
public class KafkaController {

    //@Autowired
    //private KafkaProducer kafkaProducer;

    //先搭建Zookeeper ：https://www.cnblogs.com/ding2016/p/8280696.html  （Centos7）
    //在搭建Kafka ：https://www.cnblogs.com/ding2016/p/8282907.html   （Centos7）


    @PostMapping("/producer")
    public void kafkaProducer(){
        //kafkaProducer.send();
    }
}

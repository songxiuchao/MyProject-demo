package com.example.demo.Kafka.config;

import com.example.demo.Kafka.po.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @program: demo
 * @description: kafka生产者
 * @author: xiuchao Song
 * @create: 2019-11-04 09:20
 **/

//@Component
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate <String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    /**
     * 地址 :https://blog.csdn.net/l1028386804/article/details/98381877
     *
     * 1.自动提交offset
     *
     * 2.手动提交offset   生产环境中，需要在数据消费完全后再提交offset，也就是说在数据从kafka的topic取出来后并被逻辑处理后，
     * 才算是数据被消费掉，此时需要手动去提交topic的offset
     *
     * 3.手动提交partition的offset
     */

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        //topic-ideal为主题
        kafkaTemplate.send("topic-ideal", gson.toJson(message));
    }
}

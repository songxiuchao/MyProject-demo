package com.example.demo.Kafka.po;

import lombok.Data;

import java.util.Date;

/**
 * @program: demo
 * @description: Kafka实体类
 * @author: xiuchao Song
 * @create: 2019-11-04 09:19
 **/
@Data
public class Message {
    private Long id;    //id
    private String msg; //消息
    private Date sendTime;  //时间戳
}
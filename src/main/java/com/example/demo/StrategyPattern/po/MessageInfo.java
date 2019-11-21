package com.example.demo.StrategyPattern.po;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: demo
 * @description: 消息类型
 * @author: xiuchao Song
 * @create: 2019-11-21 11:01
 **/
@Data
@AllArgsConstructor
public class MessageInfo {

    // 消息类型
    private Integer type;
    // 消息内容
    private String content;

}


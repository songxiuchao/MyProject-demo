package com.example.demo.StrategyPattern.service;

import com.example.demo.StrategyPattern.po.MessageInfo;

/**
 * @program: demo
 * @description: service
 * @author: xiuchao Song
 * @create: 2019-11-21 11:03
 **/
public interface MessageService {

    void handleMessage(MessageInfo messageInfo);
}


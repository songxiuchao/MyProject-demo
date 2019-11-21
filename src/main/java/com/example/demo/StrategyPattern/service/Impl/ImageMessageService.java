package com.example.demo.StrategyPattern.service.Impl;

import com.example.demo.StrategyPattern.Enum.MSG_TYPE;
import com.example.demo.StrategyPattern.po.MessageInfo;
import com.example.demo.StrategyPattern.service.MessageService;
import com.example.demo.StrategyPattern.annotation.MsgTypeHandler;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description: 实现类
 * @author: xiuchao Song
 * @create: 2019-11-21 11:05
 **/
@Service
@MsgTypeHandler(value = MSG_TYPE.IMAGE)
public class ImageMessageService implements MessageService {

    @Override
    public void handleMessage(MessageInfo messageInfo) {
        System.out.println("处理图片消息 " + messageInfo.getContent());
    }
}

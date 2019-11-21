package com.example.demo.StrategyPattern.controller;

import com.example.demo.StrategyPattern.po.MessageInfo;
import com.example.demo.StrategyPattern.service.MessageService;
import com.example.demo.StrategyPattern.context.MessageServiceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-21 11:09
 **/
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageTest {

    @Autowired
    MessageServiceContext messageServiceContext;

    @RequestMapping("/contextLoads")
    public void contextLoads(@RequestParam Integer type) {
        MessageService messageService = messageServiceContext.getMessageService(type);
        // 构建一个文本消息
        MessageInfo messageInfo = new MessageInfo(type, "消息内容");
        // 处理文本消息 消息内容
        // 可以看到文本消息被文本处理类所处理
        messageService.handleMessage(messageInfo);
    }
}

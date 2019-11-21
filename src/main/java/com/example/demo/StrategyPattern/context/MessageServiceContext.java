package com.example.demo.StrategyPattern.context;

import com.example.demo.StrategyPattern.service.MessageService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-21 11:06
 **/
@Component
public class MessageServiceContext {

    private final Map<Integer, MessageService> handlerMap = new HashMap<>();

    public MessageService getMessageService(Integer type) {
        return handlerMap.get(type);
    }

    public void putMessageService(Integer code, MessageService messageService) {
        handlerMap.put(code, messageService);
    }

}


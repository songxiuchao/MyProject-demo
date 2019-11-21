package com.example.demo.StrategyPattern.listener;

import com.example.demo.StrategyPattern.annotation.MsgTypeHandler;
import com.example.demo.StrategyPattern.context.MessageServiceContext;
import com.example.demo.StrategyPattern.service.MessageService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @program: demo
 * @description: 监听类
 * @author: xiuchao Song
 * @create: 2019-11-21 11:08
 **/
@Component
public class MessageServiceListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(MsgTypeHandler.class);
        MessageServiceContext messageServiceContext = event.getApplicationContext().getBean(MessageServiceContext.class);
        beans.forEach((name, bean) -> {
            MsgTypeHandler typeHandler = bean.getClass().getAnnotation(MsgTypeHandler.class);
            messageServiceContext.putMessageService(typeHandler.value().code, (MessageService) bean);
        });
    }
}

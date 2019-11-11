package com.example.demo.Mongodb.controller;

import com.example.demo.Mongodb.Enum.OrderEnum;
import com.example.demo.Mongodb.config.LogAssistant;
import com.example.demo.Mongodb.po.OrderLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: demo
 * @description: mongodb测试类
 * @author: xiuchao Song
 * @create: 2019-11-04 16:21
 **/
@Slf4j
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private LogAssistant logAssistant;

    @PostMapping("/mongo")
    public void saveLog(){
        OrderLog orderLog=new OrderLog();
        orderLog.setOrderNo("1911013700000001");
        orderLog.setMsg(OrderEnum.Success.getDesc());
       // orderLog.setTime(LocalDateTime.now());
        logAssistant.saveOrderLog(orderLog);
    }

    @PostMapping("/getLogInfo")
    public List<OrderLog> getLogInfo(){
        return logAssistant.getOrderLog("1911013700000001");
    }

}

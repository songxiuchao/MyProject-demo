package com.example.demo.Mongodb.config;

import com.example.demo.Mongodb.exception.SaveLogException;
import com.example.demo.Mongodb.po.OrderLog;
import com.example.demo.Mongodb.repository.OrderLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: demo
 * @description: 日志操作类
 * @author: xiuchao Song
 * @create: 2019-11-04 16:17
 **/
@Component
public class LogAssistant {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderLogRepository orderLogRepository;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * 保存订单日志
     */
    @Async
    public void saveOrderLog(OrderLog log) throws SaveLogException {
        log.setTime(DATE_FORMAT.format(new Date()));
        orderLogRepository.save(log);
    }

    /**
     * 查看订单日志
     * @param orderNo 订单编号
     */
    public List<OrderLog> getOrderLog(String orderNo) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderNo").is(orderNo));
        query.with(Sort.by(Sort.Order.asc("time")));
        return mongoTemplate.find(query, OrderLog.class);
    }

}

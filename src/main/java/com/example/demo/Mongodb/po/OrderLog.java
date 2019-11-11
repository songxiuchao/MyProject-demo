package com.example.demo.Mongodb.po;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @program: demo
 * @description: 订单日志实体类
 * @author: xiuchao Song
 * @create: 2019-11-04 16:11
 **/
@Document(collection = "orders_log")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class OrderLog implements Serializable {

    private static final long serialVersionUID = -5499344670104941493L;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 日志信息
     */
    private String msg;

    /**
     * 时间
     */
    private String time;
}

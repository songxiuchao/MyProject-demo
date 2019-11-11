package com.example.demo.Redis.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description: 用户信息类
 * @author: xiuchao Song
 * @create: 2019-11-02 16:20
 **/
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6581853957202636920L;

    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private int age;
    /**
     * 地址
     */
    private String address;
}

package com.example.demo.encache.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description: ehcache实体类
 * @author: xiuchao Song
 * @create: 2019-11-11 16:37
 **/
@Data
public class EhcacheEntity implements Serializable {

    private static final long serialVersionUID = -2300614622201349071L;
    /**
     * 姓名
     */
    private String name;
    /**
     * 地址
     */
    private String address;
}

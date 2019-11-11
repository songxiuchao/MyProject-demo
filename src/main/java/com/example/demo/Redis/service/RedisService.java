package com.example.demo.Redis.service;

import com.example.demo.Redis.po.User;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-02 16:17
 **/
public interface RedisService {
    /**
     * 查询用户信息
     * @return
     */
    User getUserInfo();
}

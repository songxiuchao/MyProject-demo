package com.example.demo.Redis.dao;

import com.example.demo.Redis.po.User;

public interface RedisDao {
    /**
     * 查询用户信息
     * @return
     */
    User getUserInfo();

}

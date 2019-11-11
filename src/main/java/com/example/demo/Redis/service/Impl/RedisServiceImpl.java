package com.example.demo.Redis.service.Impl;

import com.example.demo.Redis.dao.RedisDao;
import com.example.demo.Redis.po.User;
import com.example.demo.Redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: demo
 * @description: 逻辑类
 * @author: xiuchao Song
 * @create: 2019-11-02 16:18
 **/
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisDao redisDao;

    @Override
    public User getUserInfo() {
        return redisDao.getUserInfo();
    }
}

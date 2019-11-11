package com.example.demo.encache.service;

import com.example.demo.encache.po.EhcacheEntity;

/**
 * @program: demo
 * @description: ehcache
 * @author: xiuchao Song
 * @create: 2019-11-11 16:43
 **/
public interface EhcacheService {
    /**
     * 查询
     * @param name
     */
    EhcacheEntity ehcacheGetTest(String name);

    /**
     * 更新
     * @param name
     */
    EhcacheEntity ehcacheUpdateTest(String name);
}

package com.example.demo.encache.service.Impl;

import com.example.demo.encache.po.EhcacheEntity;
import com.example.demo.encache.service.EhcacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description: 实现类
 * @author: xiuchao Song
 * @create: 2019-11-11 16:46
 **/
@Slf4j
@Service("ehcacheService")
public class EhcacheServiceImpl implements EhcacheService {

    @Cacheable(value="ehcacheEntity", key="#name")
    @Override
    public EhcacheEntity ehcacheGetTest(String name) {
        EhcacheEntity ehcacheEntity=new EhcacheEntity();
        ehcacheEntity.setName("sxc_01");
        ehcacheEntity.setAddress("Qingdao_01");
        if (name.equals("sxc_01")){
            log.info("");
        }
        return ehcacheEntity;
    }

    @CachePut(value="ehcacheEntity",key="#name")
    @Override
    public EhcacheEntity ehcacheUpdateTest(String name) {
        EhcacheEntity ehcacheEntity=new EhcacheEntity();
        ehcacheEntity.setName("sxc_02");
        ehcacheEntity.setAddress("Qingdao_02");
        return ehcacheEntity;
    }
}

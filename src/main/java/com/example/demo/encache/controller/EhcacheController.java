package com.example.demo.encache.controller;

import com.example.demo.encache.po.EhcacheEntity;
import com.example.demo.encache.service.EhcacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: ehcache测试类
 * @author: xiuchao Song
 * @create: 2019-11-11 16:40
 **/
@RestController
@RequestMapping("/ehcache")
@Slf4j
public class EhcacheController {

    @Autowired
    private EhcacheService ehcacheService;

    @PostMapping("/ehcacheGetTest")
    public EhcacheEntity ehcacheGetTest(@RequestParam String name){
         return  ehcacheService.ehcacheGetTest(name);
    }

    @PostMapping("/ehcacheUpdateTest")
    public void ehcacheUpdateTest(@RequestParam String name){
        ehcacheService.ehcacheUpdateTest(name);
    }
}

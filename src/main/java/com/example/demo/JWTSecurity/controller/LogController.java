package com.example.demo.JWTSecurity.controller;

import com.example.demo.JWTSecurity.annotion.Permission;
import com.example.demo.JWTSecurity.constants.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: 日志测试类
 * @author: xiuchao Song
 * @create: 2019-11-08 14:12
 **/
@Slf4j
@RestController
@RequestMapping("/log")
public class LogController {


    @Permission(Const.ADMIN_NAME)
    @RequestMapping("addLog")
    public void addLog(){
        log.info("打印日志");
    }
}

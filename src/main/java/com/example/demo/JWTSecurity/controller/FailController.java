/*
package com.example.demo.JWTSecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @program: demo
 * @description: 错误类请求事例
 * @author: xiuchao Song
 * @create: 2019-11-08 13:33
 **//*

@Slf4j
@RestController
@RequestMapping("/error")
public class FailController {

    @RequestMapping(value = "/sessionError")
    public String sessionError(){
        return "登录失败";
    }

    @RequestMapping(value = "/error")
    public String error(){
        return "登录失败";
    }

    @RequestMapping(value = "")
    public String errorAll(){
        return "登录失败";
    }
}
*/

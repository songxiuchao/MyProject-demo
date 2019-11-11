package com.example.demo.JWTSecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @program: demo
 * @description: 测试类
 * @author: xiuchao Song
 * @create: 2019-11-09 10:57
 **/
@RestController
@RequestMapping("/sso")
public class UserController {

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}

package com.example.demo.JWTSecurity.config;

import com.example.demo.JWTSecurity.aop.PermissionAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: demo
 * @description: 资源过滤的aop
 * @author: xiuchao Song
 * @create: 2019-11-08 14:04
 **/
@Configuration
public class PermissionAopConfig {
    @Bean
    public PermissionAop permissionAop() {
        return new PermissionAop();
    }


}

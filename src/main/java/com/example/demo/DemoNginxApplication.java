package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: demo
 * @description: 启动类
 * @author: xiuchao Song
 * @create: 2019-11-12 08:38
 **/
@EnableScheduling //支持定时任务
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.Redis.dao",
        "com.example.demo.JWTSecurity.dao"})
@EnableCaching
public class DemoNginxApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoNginxApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoNginxApplication.class);

    }

}

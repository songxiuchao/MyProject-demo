package com.example.demo;

import org.apache.catalina.filters.RequestDumperFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling //支持定时任务
@SpringBootApplication
@MapperScan(basePackages = {"com.example.demo.Redis.dao",
        "com.example.demo.JWTSecurity.dao"})
public class DemoApplication  {//implements CommandLineRunner

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * 为测试环境添加相关的 Request Dumper information，便于调试
     * @return
     */
//    @Profile("!cloud")
//    @Bean
//    RequestDumperFilter requestDumperFilter() {
//        return new RequestDumperFilter();
//    }


    /*@Override
    public void run(String... args) throws Exception {
        ChannelFuture future = socketServer.start(url,port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                socketServer.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }*/
}

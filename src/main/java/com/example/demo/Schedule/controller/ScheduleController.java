package com.example.demo.Schedule.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: 定时任务测试类
 * @author: xiuchao Song
 * @create: 2019-11-09 13:37
 **/
@RestController
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {

    /**
     * cron表达式触发定时任务
     */
    @Async
    @Scheduled(cron = "1 1 * * * ? ")
    public  void test1(){
        log.info("===========> cron方式执行定时任务 {}",new Date());
    }
    /**
     * 上次任务执行结束后间隔时间执行，单位毫秒
     */
    @Async
    @Scheduled(fixedDelay = 2000)
    public void test2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(500);
        log.info("===========> fixedDelay方式执行定时任务 {}",new Date());
    }

    /**
     * 上次任务开始执行后间隔时间执行，单位毫秒
     */
    @Async
    @Scheduled(fixedRate = 200)
    public void test3() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2000);
        log.info("===========> fixedRate方式执行定时任务 {}",new Date());
    }

    @PostMapping("/scheduleTest")
    public void scheduleTest(){
        log.info("开始执行:  "+ LocalDateTime.now());
        test1();
        log.info("结束执行:  "+LocalDateTime.now());
    }


}

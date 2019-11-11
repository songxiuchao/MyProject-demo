package com.example.demo.Schedule.controller;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description: quartz定时任务
 * @author: xiuchao Song
 * @create: 2019-11-11 08:43
 **/
@Slf4j
public class QuartzJob1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("=====job1===开始执行 {}",System.currentTimeMillis());
        //======================业务逻辑==========================
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //=======================================================
        log.info("=====job1===结束执行 {}",System.currentTimeMillis());
    }
}

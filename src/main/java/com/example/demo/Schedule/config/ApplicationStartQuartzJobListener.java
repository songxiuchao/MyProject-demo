package com.example.demo.Schedule.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @program: demo
 * @description: 配置监听器
 * @author: xiuchao Song
 * @create: 2019-11-11 08:48
 **/
//@Configuration   //先注掉需要的时候开启
public class ApplicationStartQuartzJobListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MyQuartzScheduler myQuartzScheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            //启动定时任务
            myQuartzScheduler.startJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public Scheduler scheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }
}

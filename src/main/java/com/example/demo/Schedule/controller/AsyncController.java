package com.example.demo.Schedule.controller;

import com.example.demo.Schedule.config.AsyncConfig;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @program: demo
 * @description: Async测试类
 * @author: xiuchao Song
 * @create: 2019-11-09 13:58
 **/
@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    @Value("${corePoolSize}")
    private int corePoolSize;

    @Value("${maxPoolSize}")
    private int maxPoolSize;

    @Value("${queueCapacity}")
    private int queueCapacity;

    @Value("${keepAliveTime}")
    private long keepAliveTime;

    //@Async
    @PostMapping("/asyncTest")
    public void AsyncTest(){
        /**
         * 手动创建线程池
         */
        // 创建线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("xxx-pool-%d").build();
        // 创建通用线程池
        /**
         * 参数含义：
         *      corePoolSize : 线程池中常驻的线程数量。核心线程数，默认情况下核心线程会一直存活，即使处于闲置状态也不会受存keepAliveTime限制。除非将allowCoreThreadTimeOut设置为true。
         *      maximumPoolSize : 线程池所能容纳的最大线程数。超过这个数的线程将被阻塞。当任务队列为没有设置大小的LinkedBlockingDeque时，这个值无效。
         *      keepAliveTime : 当线程数量多于 corePoolSize 时，空闲线程的存活时长，超过这个时间就会被回收
         *      unit : keepAliveTime 的时间单位
         *      workQueue : 存放待处理任务的队列，该队列只接收 Runnable 接口
         *      threadFactory : 线程创建工厂
         *      handler : 当线程池中的资源已经全部耗尽，添加新线程被拒绝时，会调用RejectedExecutionHandler的rejectedExecution方法，参考 ThreadPoolExecutor 类中的内部策略类
         *///corePoolSize, maxPoolSize, keepAliveTime,
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        System.out.println("main开始时间:"+MyRunnable.now());
            MyRunnable myRunnable = new MyRunnable("thread");
            System.out.println(myRunnable.getName()+"开始时间:"+MyRunnable.now());
            threadPoolExecutor.execute(() ->{
                log.info("执行任务开始=======》");
                myRunnable.run();
                log.info("执行任务结束=======》");
                    });
            threadPoolExecutor.shutdown();//7秒后不再接受执行线程
             while (!threadPoolExecutor.isTerminated()){
                    //等待所有线程结束
                 threadPoolExecutor.shutdown();//7秒后不再接受执行线程
             }
        System.out.println("main结束时间:"+MyRunnable.now());
    }

    /**
     * 定义一个静态内部类
     */
    static class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        @Override
        public void run() {
            System.out.println(getName() + "true start:" + now());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "true end:" + now());
        }

        static LocalDateTime now() {
            return LocalDateTime.now();
        }

    }
}

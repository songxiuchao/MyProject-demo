package com.example.demo.dynamic.datasource.datasource;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sxc
 * @version 1.0
 * description: 数据源缓存释放调度器
 * @date 2020/5/5 9:29
 */
public enum DatasourceScheduler {
    /**
     * 当前实例
     */
    INSTANCE;

    private AtomicInteger cacheTaskNumber = new AtomicInteger(1);
    private ScheduledExecutorService scheduler;

    DatasourceScheduler() {
        create();
    }

    private void create() {
        this.shutdown();
        this.scheduler = new ScheduledThreadPoolExecutor(10, r -> new Thread(r, String.format("Datasource-Release-Task-%s", cacheTaskNumber.getAndIncrement())));
    }

    private void shutdown() {
        if (null != this.scheduler) {
            this.scheduler.shutdown();
        }
    }
    //ScheduledFuture <？> scheduleAtFixedRate（Runnable command，long initialDelay，
    // long period，TimeUnit unit）创建并执行一个周期性操作，该操作将在给定的初始延迟后首先启用，
    // 然后在给定的周期内启用；也就是说执行将在initialDelay，initialDelay + period，
    // initialDelay + 2 * period等之后开始。如果任务的任何执行遇到异常，则将禁止后续执行。
    // 否则，任务将仅通过取消或终止执行程序而终止。如果此任务的任何执行花费的时间超过其时间，
    // 则后续执行可能会开始得较晚，但不会同时执行
    public void schedule(Runnable task,long delay){
        this.scheduler.scheduleAtFixedRate(task, delay, delay, TimeUnit.MILLISECONDS);
    }

}


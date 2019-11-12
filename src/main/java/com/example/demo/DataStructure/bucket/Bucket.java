package com.example.demo.DataStructure.bucket;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @program: 限流令牌桶
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-12 16:21
 **/
public class Bucket{
    /**
     * 设置的最大流量
     */
    private final int maxFlow;
    /**
     * 单位（毫秒）
     */
    private final int  unitTime;

    /**
     * 动作
     */
    private final String action;

    public Bucket(int maxFlow, int unitTime, String action) {
        this.maxFlow = maxFlow;
        this.unitTime = unitTime;
        this.action = action;
        addToKen();
    }
    // 手动创建通用线程池
    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,0,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024),
            new ThreadFactoryBuilder().setNameFormat("xxx-pool-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
    private  Map<String,Integer> tokenBucket = new ConcurrentHashMap<>();

    //private ExecutorService e = Executors.newFixedThreadPool(1);

    /**
     *  开启异步线程往桶里放token
     */
    public void addToKen(){
        threadPoolExecutor.execute(new MyTask());
    }

    //实现Runnable接口
    class MyTask implements Runnable{
        //重写run方法
        @Override
        public void run() {
            //任务内容....
            while(true){
                tokenBucket.put(action,maxFlow);
                try {
                    Thread.sleep(unitTime);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
//    public void addToKen(){
//        e.submit(new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    tokenBucket.put(action,maxFlow);
//                    try {
//                        Thread.sleep(unitTime);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//            }
//        });
//    }


    public boolean getToken(){
        System.out.println("-------"+tokenBucket.get(action));
        if(tokenBucket.get(action)==null||tokenBucket.get(action)<=0){
            return false;
        }else{
            tokenBucket.put(action,tokenBucket.get(action)-1);
            return true;
        }

    }



}
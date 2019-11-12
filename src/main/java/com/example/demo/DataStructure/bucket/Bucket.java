package com.example.demo.DataStructure.bucket;

/**
 * @program: 限流令牌桶
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-12 16:21
 **/
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 *限流桶示例
 *
 *
 * Created by sult on 2018/8/17.
 */
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

    private  Map<String,Integer> tokenBucket = new ConcurrentHashMap<>();

    private ExecutorService e = Executors.newFixedThreadPool(1);

    /**
     *  开启异步线程往桶里放token
     */
    public void addToKen(){
        e.submit(new Runnable() {
            @Override
            public void run() {
                while(true){
                    tokenBucket.put(action,maxFlow);
                    try {
                        Thread.sleep(unitTime);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }


    public boolean getToken(){
        System.out.println("---1---"+tokenBucket.get(action));
        if(tokenBucket.get(action)==null||tokenBucket.get(action)<=0){

            return false;
        }else{
            tokenBucket.put(action,tokenBucket.get(action)-1);
            return true;
        }

    }



}
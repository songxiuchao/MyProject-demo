package com.example.demo.redis.controller;

import com.example.demo.redis.config.JedisUtil;
import com.example.demo.redis.config.RedisDistributeLock;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.naming.directory.SearchResult;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author guowei
 * @version 1.0
 * @description 测试redis集群
 * @date 2019/4/2 14:09
 */
@RestController
@RequestMapping("/redis")
public class TestController {
    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private RedisDistributeLock distributeLock;

    @Autowired
    StringRedisTemplate template;

    @Autowired
    private JedisPool jedisPool;

    /**
     * http://127.0.0.1:8081/setUserRedis
     * @return
     */
    @RequestMapping(value = "/setString")
    String setString(){
        jedisUtil.set("UserRedis","a");
        return "success";
    }

    /**
     * http://127.0.0.1:8081/getString
     * @return
     */
    @RequestMapping(value = "/getString")
    String getString(){
        return jedisUtil.get("UserRedis");
    }

    /**
     * http://127.0.0.1:8081/setUserRedis
     * @return
     */
    @RequestMapping(value = "/setUserRedis")
    String setUserRedis(){
        jedisUtil.setObject("UserRedisObject",new UserRedis("test",12),100);
        return "success";
    }

    /**
     * http://127.0.0.1:8081/getUserRedis
     * @return
     */
    @RequestMapping(value = "/getUserRedis")
    UserRedis getUserRedis(){
       UserRedis UserRedis= jedisUtil.getObject("UserRedisObject",UserRedis.class);
       return UserRedis;
    }

    /**
     * 集群分布式锁
     * http://127.0.0.1:8081/testLock
     * @return
     */
    @RequestMapping(value = "/testLock")
    String testLock(){
        // 创建线程工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("xxx-pool-%d")
                .build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 200, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        jedisUtil.set("test_inc","0");
        for (int i = 0; i < 100; i++) {
            int index = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        if(distributeLock.tryLock("TEST_LOCK_KEY","TEST_LOCK_VAL_"+ index,1000*10,1000*10)){
                        if(distributeLock.tryLock("TEST_LOCK_KEY","TEST_LOCK_VAL_"+ index,1000*5,10,10)){
//                            System.out.println("get lock success:---" + "TEST_LOCK_VAL_"+ index);

                            int test_inc = Integer.valueOf(jedisUtil.get("test_inc"));
                            String uid = UUID.randomUUID().toString();
                            if(test_inc<10){
                                jedisUtil.inc("test_inc");
                                System.out.println("用户：" + uid + "抢购成功,人数:" + (test_inc + 1));
                            }else{
                                System.out.println("用户：" + uid + "抢购失败");
                                return;
                            }
                            if (!distributeLock.tryUnLock("TEST_LOCK_KEY", "TEST_LOCK_VAL_"+ index)){
                                throw new RuntimeException("release lock fail");
                            }
//                            System.out.println("release lock success:---" + "TEST_LOCK_VAL_"+ index);
                        } else {
//                            System.out.println("get lock fail :---" + "TEST_LOCK_VAL_"+ index);
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        threadPoolExecutor.shutdown();
        return "success";
    }

    /**
     * 集群分布式锁
     * http://127.0.0.1:8081/init
     * @return
     */
    @RequestMapping(value = "/init")
    void init(){
        jedisUtil.set("test_inc","0");
    }

    /**
     * 集群分布式锁 使用jmeter测试
     * http://127.0.0.1:8081/testLockByTool
     * @return
     */
    @RequestMapping(value = "/testLockByTool")
    void testLockByTool(){
        String uid = UUID.randomUUID().toString();
        System.out.println(uid+":"+System.currentTimeMillis());
        //两种方式进行获取锁
//        if(distributeLock.tryLock("TEST_LOCK_KEY1","TEST_LOCK_VAL_"+ uid,1000*5,1000*10)){
        if(distributeLock.tryLock("TEST_LOCK_KEY1","TEST_LOCK_VAL_"+ uid,1000*5,10,10)){
            System.out.println("get lock success:---" + "TEST_LOCK_VAL_"+ uid+":"+System.currentTimeMillis());
            int test_inc = Integer.valueOf(jedisUtil.get("test_inc"));
            if(test_inc<10){
                jedisUtil.inc("test_inc");
                System.out.println("用户：" + uid + "抢购成功,人数:" + (test_inc + 1)+":"+System.currentTimeMillis());
            }else{
                System.out.println("用户：" + uid + "抢购失败"+":"+System.currentTimeMillis());
            }
            if (!distributeLock.tryUnLock("TEST_LOCK_KEY1", "TEST_LOCK_VAL_"+ uid)){
                throw new RuntimeException("release lock fail");
            }
            System.out.println("release lock success:---" + "TEST_LOCK_VAL_"+ uid+":"+System.currentTimeMillis());
        } else {
            System.out.println("get lock fail :---" + "TEST_LOCK_VAL_"+ uid);
        }
    }

    @RequestMapping(value = "/syncmessage")
    public String SyncMessage(){
        for(int i = 1; i <= 5; i++){
            try{
                // 为了模拟消息，sleep一下。
                Thread.sleep(2000);
            }catch(InterruptedException ex){}
            template.convertAndSend("channel:test", String.format("我是消息{%d}号: %tT", i, new Date()));
        }

        return "5";
    }

    /**
     * HyperLogLog
     */
    @RequestMapping(value = "/hyperLogLog")
    public void testHyper(){
        Jedis jedis = new Jedis();
        for (int i = 0; i < 100000; i++) {
            jedis.pfadd("codehole", "user" + i);
        }
        long total = jedis.pfcount("codehole");
        System.out.printf("%d %d\n", 100000, total);
        jedis.close();
    }

    /**
     * GEO
     * @param map
     * @return
     */
    @RequestMapping("/geoadd")
    public Long geoadd(Map<String, GeoCoordinate> map){
        Jedis jedis=null;
        try{
            jedis = jedisPool.getResource();
            return jedis.geoadd("key", map );
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            jedis.close();
        }
        return null;
    }


    /**
     * redisSearch
     */
    @RequestMapping("/redisSearch")
    public void redisSearch(){

    }


}

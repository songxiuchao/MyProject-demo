package com.example.demo.DataStructure.otherNum.哈希表.ConcurrentHashMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author idea
 * @data 2018/12/14
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        //关于hashmap造成死循环的案例
//        HashMap<String,String> hashMap=new HashMap<>(2);
//        Thread t=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i=0;i<1000;i++){
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            hashMap.put(UUID.randomUUID().toString(),"");
//                        }
//                    },"threadname"+i).start();
//                }
//            }
//        },"totalthreadname");
//        t.start();
//        t.join();
        //插入数据
//        ConcurrentHashMap<String,String> concurrentHashMap=new ConcurrentHashMap<>();
//        concurrentHashMap.put("Key","Value");
//        String value=concurrentHashMap.get("Key");
//        System.out.println(value);
//        System.out.println(concurrentHashMap.containsKey("Key"));
//
//        System.out.println("--------");
//        ConcurrentHashMap.KeySetView<String,String> keySetView=concurrentHashMap.keySet();
//        for (String s : keySetView) {
//            System.out.println(s);
//        }

        System.out.println(change(10));
    }

    /**
     * <<      :     左移运算符，num << 1,相当于num乘以2
     *
     * >>      :     右移运算符，num >> 1,相当于num除以2
     *
     * >>>    :     无符号右移，忽略符号位，空位都以0补齐
     */

    public static int change(int c){
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        return n;
    }

}

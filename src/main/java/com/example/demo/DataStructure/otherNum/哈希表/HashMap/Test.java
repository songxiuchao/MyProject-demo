package com.example.demo.DataStructure.otherNum.哈希表.HashMap;

import java.util.HashMap;

/**
 * 作者：idea
 * 日期：2018/6/25
 * 描述：
 */
public class Test {


    public static void main(String[] args) {
//        MyHashMap mh=new MyHashMap();
//        for(int i=0;i<10000;i++){
//            mh.put(i,i);
//        }
//        for(int i=0;i<10000;i++) {
//            if (mh.get(i) != null) {
//                System.out.println(mh.get(i));
//            }
//        }
        HashMap hashMap=new HashMap();
        hashMap.put("idea2",1);
        hashMap.put("idea3",2);
        hashMap.put("idea4",3);
        hashMap.put("idea5",4);
        hashMap.put("idea7",5);
        hashMap.put("idea00",6);
        for (Object object : hashMap.keySet()) {
            System.out.println(hashMap.get(object));
        }
    }
}

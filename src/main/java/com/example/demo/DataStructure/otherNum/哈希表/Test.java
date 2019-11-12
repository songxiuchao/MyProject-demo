package com.example.demo.DataStructure.otherNum.哈希表;

import java.util.Objects;
import java.util.HashSet;

/**
 * 作者：idea
 * 日期：2018/6/25
 * 描述：
 */
public class Test {


    public static void main(String[] args) {
        MyHashMap mh=new MyHashMap();
        for(int i=0;i<10000;i++){
            mh.put(i,i);
        }
        for(int i=0;i<10000;i++) {
            if (mh.get(i) != null) {
                System.out.println(mh.get(i));
            }
        }

    }
}

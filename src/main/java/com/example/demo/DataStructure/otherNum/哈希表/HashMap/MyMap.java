package com.example.demo.DataStructure.otherNum.哈希表.HashMap;

/**
 * 作者：idea
 * 日期：2018/6/25
 * 描述：
 */
public interface MyMap<K,V> {
    public V get(K key);
    public V put(K key, V value);
    interface IEntry<K,V>{
        public K getKey();
        public V getValue();
    }

}

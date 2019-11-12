package com.example.demo.DataStructure.otherNum.哈希表.retry;

import java.util.HashMap;

/**
 * @author idea
 * @data 2018/9/2
 */
public class Entry<K,V> implements MyMap<K,V> {

    private K key;
    private V value;

    public final K getKey() {
        return key;
    }

    public final K setKey(K key) {
        K oldKey=this.key;
        this.key = key;
        return oldKey;
    }

    public final V getValue() {
        return value;
    }

    public final V setValue(V value) {
        V oldValue=this.value;
        this.value = value;
        return oldValue;
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

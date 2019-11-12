package com.example.demo.DataStructure.otherNum.哈希表.HashMap;

/**
 * 作者：idea
 * 日期：2018/6/25
 * 描述：
 */
public class Entry<K,V> implements MyMap.IEntry<K,V> {

    private K key;
    private V value;
    public Entry<K,V> next;

    public Entry(){}

    public Entry(K key, V value, Entry<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key=" + key +
                ", value=" + value +
                ", next=" + next +
                '}';
    }
}

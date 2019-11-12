package com.example.demo.DataStructure.otherNum.算法部分.缓存淘汰算法.LRU;

/**
 * @author idea
 * @data 2019/7/22
 */
public class LRUNode {

    private String key;

    private String value;

    private LRUNode pre;

    private LRUNode next;



    public LRUNode(String key, String value, LRUNode pre, LRUNode next) {
        this.key = key;
        this.value = value;
        this.pre = pre;
        this.next = next;
    }

    public LRUNode(){

    }

    public String getKey() {
        return key;
    }

    public LRUNode setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public LRUNode setValue(String value) {
        this.value = value;
        return this;
    }

    public LRUNode getPre() {
        return pre;
    }

    public LRUNode setPre(LRUNode pre) {
        this.pre = pre;
        return this;
    }

    public LRUNode getNext() {
        return next;
    }

    public LRUNode setNext(LRUNode next) {
        this.next = next;
        return this;
    }
}

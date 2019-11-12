package com.example.demo.DataStructure.otherNum.算法部分.缓存淘汰算法.LRU;

import java.util.Scanner;

/**
 * 最基本的lru缓存模型
 *
 * @author idea
 * @data 2019/7/22
 */
public class LRUDemo {

    private int limit;

    private LRUNode head = new LRUNode();

    private LRUNode tail = new LRUNode();

    private int size;

    public LRUDemo(int limit) {
        if (limit <= 0) {
            throw new RuntimeException("初始化大小异常");
        }
        this.limit = limit;
    }

    public void put(String key, String value) {
        LRUNode newNode = new LRUNode(key, value, null, null);
        if (size == 0) {
            head.setNext(newNode);
            tail.setPre(newNode);
            newNode.setPre(head);
            newNode.setNext(tail);
            size++;
        } else if (size > 0 && size < limit) {
            LRUNode currentFirst = head.getNext();
            head.setNext(newNode);
            newNode.setNext(currentFirst);
            newNode.setPre(head);
            currentFirst.setPre(newNode);
            size++;
        } else if (size == limit) {
            removeOldNode();
            LRUNode currentFirst = head.getNext();
            newNode.setNext(currentFirst);
            newNode.setPre(head);
            head.setNext(newNode);
            currentFirst.setPre(newNode);
        }
    }

    public Object get(String key) {
        LRUNode node = head;
        while (node != tail) {
            node = node.getNext();
            if (node.getKey().equals(key)) {
                refeshNode(node);
                return node.getValue();
            }
        }
        return null;
    }

    /**
     * 打印lru链表里面的数据信息
     */
    public void displayNode() {
        LRUNode node = head;
        while (node != tail) {
            String temp = "";
            if (node.getNext() != null && node.getPre() != null) {
                temp = "<-->";
            } else if (node.getNext() != null && node.getPre() == null) {
                temp = "-->";
            } else if (node.getPre() != null && node.getNext() == null) {
                temp = "<--";
            }
            String value = node.getValue();
            if (value == null) {
                value = "node";
            }
            System.out.print(value + temp);
            node = node.getNext();
        }
        String value = node.getValue();
        if (value == null) {
            value = "node";
        }
        System.out.println(node.getValue());
    }

    /**
     * 移除旧的节点
     */
    public void removeOldNode() {
        LRUNode currentLast = tail.getPre().getPre();
        currentLast.setNext(tail);
        tail.setPre(currentLast);
    }

    /**
     * 刷新lru队列信息
     */
    public void refeshNode(LRUNode node) {
        LRUNode before = node.getPre();
        LRUNode after = node.getNext();
        before.setNext(after);
        after.setPre(before);

        LRUNode currentFirst = head.getNext();
        currentFirst.setPre(node);
        head.setNext(node);
        node.setPre(head);
        node.setNext(currentFirst);

    }


    public static void main(String[] args) {
        LRUDemo lruDemo = new LRUDemo(5);
        lruDemo.put("k1", "1");
        lruDemo.put("k2", "2");
        lruDemo.put("k3", "3");
        lruDemo.put("k4", "4");
        lruDemo.put("k5", "5");
        while(true){
            System.out.println("请输入key:");
            Scanner scanner=new Scanner(System.in);
            String key=scanner.nextLine();
            lruDemo.get(key);
            System.out.println("查询数据:");
            lruDemo.displayNode();
        }

    }
}

package com.example.demo.DataStructure.otherNum.队列;

/**
 * 数组队列
 *
 * @author idea
 * @data 2018/10/25
 */
public class ArrayQueue {

    private String[] items;

    private int capacity = 0;

    private int head = 0;

    private int tail = 0;

    public ArrayQueue(int size) {
        items = new String[size];
        capacity = size;
    }

    /**
     * 入队
     *
     * @param value
     * @return
     */
    public boolean enqueue(String value) {
        //无法添加多余元素
        if (tail == capacity) {
            return false;
        }
        items[tail] = value;
        tail++;
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if(head==tail){
            return null;
        }
        String value = items[head];
        head++;
        return value;
    }

    /**
     * 打印相应的内容
     */
    public void display(){
        for(int i=head;i<tail;i++){
            System.out.println(items[i]);
        }
    }

}

package com.example.demo.DataStructure.otherNum.栈;

/**
 * 链式栈
 *
 * @author idea
 * @data 2018/10/24
 */
public class LinkedListStack {

    private Node top = null;

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        if (top == null) {
            top = new Node(value, null);
        } else {
            Node newNode = new Node(value, top);
            top = newNode;
        }
    }

    /**
     * 出栈
     * @return
     */
    public Integer pop() {
        if (top == null) {
            return null;
        }
        Integer value=top.data;
        top=top.next;
        return value;
    }

    /**
     * 打印栈的内容
     */
    public void printAll(){
        while(top!=null){
            System.out.println(top.data);
            top=top.next;
        }
    }

    /**
     * 节点
     */
    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}

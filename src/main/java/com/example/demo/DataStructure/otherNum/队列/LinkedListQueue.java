package com.example.demo.DataStructure.otherNum.队列;

/**
 * 链表类型队列
 *
 * @author idea
 * @data 2018/10/25
 */
public class LinkedListQueue {

    private Node head;
    private Node tail;

    /**
     * 入队
     *
     * @param value
     * @return
     */
    public boolean enqueue(String value) {
        if (tail == null) {
            head = new Node(value, null);
            tail = head;
        }else {
            Node newNode = new Node(value, null);
            tail.next = newNode;
            tail = tail.next;
        }
        return true;
    }

    /**
     * 出队
     *
     * @return
     */
    public String dequeue() {
        if (head == null) {
            return null;
        }
        if (head == tail) {
            return null;
        }
        String value = head.getData();
        head = head.next;
        return value;
    }

    /**
     * 打印所有内容
     */
    public void printAll() {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public static class Node {
        private String data;
        private Node next;

        public Node(String data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String getData() {
            return data;
        }
    }

}

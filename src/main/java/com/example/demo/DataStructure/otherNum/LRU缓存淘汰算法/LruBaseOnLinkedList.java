package com.example.demo.DataStructure.otherNum.LRU缓存淘汰算法;

import com.sun.org.apache.xerces.internal.impl.dv.xs.AbstractDateTimeDV;

/**
 * 基于链表的lru缓存淘汰算法
 *
 * @author idea
 * @data 2018/12/24
 */
public class LruBaseOnLinkedList {

    Node head = new Node(0, null);

    /**
     * 默认链表容量
     */
    private final static Integer DEFAULT_CAPACITY = 10;

    /**
     * 链表长度
     */
    private Integer length=0;

    /**
     * 链表容量
     */
    private Integer capacity;

    public LruBaseOnLinkedList() {
        capacity = DEFAULT_CAPACITY;
    }

    private class Node {
        Node next;
        Integer data;

        public Node(Integer data, Node next) {
            this.next = next;
            this.data = data;
        }
    }


    private void addData(Integer data) {
        Node newNode = new Node(data, null);
        if (length == 0) {
            head.next = newNode;
        } else {
            Node temp = head;
            Node nextNode = temp.next;
            temp.next = newNode;
            newNode.next = nextNode;
        }
        length++;
    }

    public void deleteNode(Node node) {
        Node temp = head;
        Node preNode;
        while (temp.next != null) {
            preNode = temp;
            temp = temp.next;
            if (temp.data.equals(node.data)) {
                preNode.next = temp.next;
                break;
            }
        }
        length--;
    }

    public void add(Integer data) {
        Node node = findNode(data);
        if (node != null) {
            deleteNode(node);
            addData(node.data);
            return;
        }
        Node newNode=new Node(data,null);
        addData(newNode.data);
        clearTailNode();
    }

    /**
     * 清除尾部数据
     */
    public void clearTailNode(){
        if(length.equals(capacity+1)){
            Node temp=head;
            Node preNode=head;
            while (temp.next != null) {
                preNode=temp;
                temp = temp.next;
            }
            preNode.next=null;
            length--;
        }
    }


    public void display(){
        Node temp=head;
        System.out.print("head->");
        while (temp.next != null) {
            temp=temp.next;
            System.out.print(temp.data+"->");
        }
    }

    private Node findNode(Integer data) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (data.equals(temp.data)) {
                return temp;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LruBaseOnLinkedList lruBaseOnLinkedList=new LruBaseOnLinkedList();
        lruBaseOnLinkedList.add(1);
        lruBaseOnLinkedList.add(2);
        lruBaseOnLinkedList.add(3);
        lruBaseOnLinkedList.add(4);
        lruBaseOnLinkedList.add(5);
        lruBaseOnLinkedList.add(6);
        lruBaseOnLinkedList.add(7);
        lruBaseOnLinkedList.add(8);
        lruBaseOnLinkedList.add(9);
        lruBaseOnLinkedList.add(10);
        lruBaseOnLinkedList.add(11);
        lruBaseOnLinkedList.add(12);
        lruBaseOnLinkedList.add(13);
        lruBaseOnLinkedList.add(14);
        lruBaseOnLinkedList.add(15);
        lruBaseOnLinkedList.add(16);
        lruBaseOnLinkedList.add(17);
        lruBaseOnLinkedList.add(18);

        lruBaseOnLinkedList.display();

    }


}

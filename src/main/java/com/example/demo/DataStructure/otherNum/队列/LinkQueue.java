package com.example.demo.DataStructure.otherNum.队列;

/**
 * 作者：idea
 * 日期：2018/5/26
 * 描述：链式队列
 */
public class LinkQueue<T> {

    class Node{
        T data;
        Node next;
        Node(T data){
            this.data=data;
        }
        Node(){
        }
    }
    private Node front;
    private Node rear;
    private int size=0;

    public  LinkQueue(){
        rear=new Node();
        front=new Node();
    }

    public  LinkQueue(T data){
        front=new Node(data);
        rear=front;
        size++;
    }

    public void add(T data){
        Node newNode=new Node(data);
        if(size==0){
            newNode.next=rear;
            front.next=newNode;
        }else{
            Node temp=front.next;
            while (temp.next!=rear){
                temp=temp.next;
            }
            temp.next=newNode;
            newNode.next=rear;
        }
        size++;
    }

    public void push(){
        if(size==0){
            throw new RuntimeException("队列已为空");
        }
        Node newNode=new Node();
        Node first=front.next;
        front.next=first.next;
        size--;
    }

    public void display(){
        Node temp;
        temp=front.next;
        while (temp.next!=null&&temp.data!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
    }

    public static void main(String[] args) {
        LinkQueue lq=new LinkQueue();
        lq.add(1);
        lq.add(2);
        lq.add(3);
        lq.add(4);
        lq.push();
        lq.display();
    }


}

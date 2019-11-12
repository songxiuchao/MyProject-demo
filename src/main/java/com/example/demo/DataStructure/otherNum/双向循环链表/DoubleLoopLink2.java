package com.example.demo.DataStructure.otherNum.双向循环链表;

/**
 * 作者：idea
 * 日期：2018/6/27
 * 描述：
 */
public class DoubleLoopLink2 {

    Node head;
    Node tail;
    transient int size=0;
    int data;

    private class Node{
        public int data;
        public Node pre;
        public Node next;
        Node(int data,Node pre,Node next){
            this.data=data;
            this.pre=pre;
            this.next=next;
        }
        Node(int data){
            this.data=data;
        }
        Node(){}
    }

    public DoubleLoopLink2(int size,Node head,Node tail){
        this.size=size;
        this.head=head;
        this.tail=tail;
    }

    public DoubleLoopLink2(){
        head=new Node();
        tail=new Node();
        head.next=tail;
        head.pre=tail;
        tail.next=head;
        tail.pre=head;
    }

    private void addFirstNode(int data){
        Node firstNode=new Node(data);
        head.next=firstNode;
        firstNode.pre=head;
        firstNode.next=tail;
        tail.pre=firstNode;
        size++;
    }

    public void add(int data){
        if(head.next==tail){ //如果这个双向循环链表是空的话，就调用添加第一个节点的函数
            addFirstNode(data);
        }else{
            Node currentNode=head;
            while (currentNode.next!=tail){
                currentNode=currentNode.next;
            }
            Node newNode=new Node(data);
            currentNode.next=newNode;
            newNode.pre=currentNode;
            newNode.next=tail;
            tail.pre=newNode;
            size++;
        }
    }

    private void removeFirstNode(){
        if(head.next==tail){
            throw new RuntimeException("index error！");
        }
        Node firstNode=head.next;
        head.next=firstNode.next;
        firstNode=null;
    }

    public void remove(int index){
        int cursor=0; //游标指针
        Node currentNode=head;
        if(index==0){
            removeFirstNode();
        }else{
            while (currentNode.next!=tail){
                currentNode=currentNode.next;
                if(index==cursor){
                    break;
                }
                cursor++;
            }
            Node preNode=currentNode.pre;
            Node nextNode=currentNode.next;
            preNode.next=nextNode;
            nextNode.pre=preNode;
            currentNode=null;
        }
        size--;
    }

    public void display(){
        Node currentNode=head;
        while (currentNode.next!=tail){
            currentNode=currentNode.next;
            System.out.println(currentNode.data);
        }
    }

    public static void main(String[] args) {
        DoubleLoopLink2 dp=new DoubleLoopLink2();
        dp.add(1);
        dp.add(2);
        dp.add(3);
        dp.add(4);
        dp.remove(1);
        dp.display();
    }





}

package com.example.demo.DataStructure.otherNum.双向队列;


/**
 * 作者：idea
 * 日期：2018/5/11
 * 描述：双向队列的实现
 */
public class Deque {
    Node head;
    Node tail;
    //申明该变量不会被持久化
    transient int size=0;
    int data;
    class Node{
        int data;
        Node pre;
        Node next;
        Node(int data,Node pre,Node next){
            this.data=data;
            this.pre=pre;
            this.next=next;
        }
        Node(int data){
            this.data=data;
        }
        Node(){
        }
    }

    public Deque(int size,Node head,Node tail){
        this.size=0;
        this.head=head;
        this.tail=tail;
    }

    public Deque(){
        head=new Node();
        tail=new Node();
    }


    //添加头部节点(只有一个元素的时候)
    public void addHeadNode(int data){
        Node node=new Node(data,null,null);
        head.next=node;
        node.pre=head;
        node.next=tail;
        tail.pre=node;
        size++;
    }

    //尾插法
    public void addLast(int data){
        if(head.next==null){
            addHeadNode(data);
        }else{
            Node temp=head;
            Node newNode=new Node(data);
            for(int i=0;i<size;i++){
                temp=temp.next;
            }
            newNode.pre=temp;
            newNode.next=tail;
            temp.next=newNode;
            tail.pre=newNode;
            size++;
        }
    }

    //头插法
    public void addBegin(int data){
//        add(0,data);
        if(head.next==null){
            addHeadNode(data);
            size++;
        }else {
            Node newNode = new Node(data);
            newNode.next = head.next;
            newNode.pre = head;
            head.next = newNode;
            size++;
        }
    }


    //根据索引，删除某一个链表元素内容
    public String remove(int index){
        Node temp=head;
        if(index>=size){
            return "下标超值";
        }
        for(int i=0;i<size;i++){
            //并非最后一个数值
            if(i==index){
                temp.next=temp.next.next;
                temp.next.pre=temp;
                size--;
                break;
            }
            temp=temp.next;
        }
        return null;
    }

    public String removeFirst(){
        return remove(0);
    }

    public String removeLast(){
        return remove(size-1);
    }


    public String disPlay(){
        Node temp=head.next;
        StringBuffer result=new StringBuffer("[head<==>");
        int index=0;
        for(int i=0;i<size;i++){
            index++;
            result.append(temp.data+"<==>");
            temp=temp.next;
        }
        result.append("tail]");
        return result.toString();
    }

    public static void main(String[] args) {
        Deque deque=new Deque();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addBegin(34);
        deque.addLast(44);
        deque.removeFirst();
        deque.removeLast();
        System.out.println(deque.disPlay());
    }





}

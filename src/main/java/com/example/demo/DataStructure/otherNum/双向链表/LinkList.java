package com.example.demo.DataStructure.otherNum.双向链表;

import com.sun.corba.se.impl.orbutil.graph.NodeData;

import java.util.LinkedHashMap;

/**
 * 作者：idea
 * 日期：2018/5/11
 * 描述：双向链表的实现(单向类型)
 */
public class LinkList {
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

    public LinkList(int size,Node head,Node tail){
        this.size=0;
        this.head=head;
        this.tail=tail;
    }

    public LinkList(){
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

    //从指定索引位置插入相应的内容
//    public void add(int index,int data){
//        //链表里面已经有相应的元素了
////        if(head.next!=null){
//            Node temp=head;
//            Node newNode=new Node(data);
//            if(index==0){
//                newNode.pre=head;
//                newNode.next=head.next;
//                head.next=newNode;
//            }
//            if(index<size&&index>0){ //最后一个节点处插入需要特殊处理
//                for(int i=0;i<index;i++){
//                    temp=temp.next;
//                }
//                newNode.next=temp.next;
//                newNode.pre=temp;
//                temp.next.pre=newNode;
//                temp.next=newNode;
//            }
//            if(index==size&&size!=0){
//                for(int i=0;i<index;i++){
//                    temp=temp.next;
//                }
//                temp.next=newNode;
//                newNode.pre=temp;
//                newNode.next=tail;
//                tail.pre=newNode;
//            }
//            size++;
////        }
//
//    }


    public Node get(int index){
        if(index>size || index<0){
            throw new RuntimeException("链表下标超出范围了！");
        }
        Node currentNode=head;
        for(int j=0;j<size;j++){
            currentNode=head.next;
            if(j==index){
                return currentNode;
            }
        }
        return null;
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

    public void removeData(Integer data){
        Node temp=head;
        Node preNode;
        for(int i=0;i<size;i++){
            preNode=temp;
            temp=temp.next;
            if(temp.data==data){
                preNode.next=temp.next;
                size--;
                break;
            }
        }
    }

    public void removeNode(Node itemNode){
      Node preNode=itemNode.pre;
      Node nextNode=itemNode.next;
      preNode.next=nextNode;
      size--;
    }

    public String removeFirst(){
        return remove(0);
    }

    public String removeLast(){
//        if(size==2){ //剩余头尾两个节点和一个元素的时候
//            remove(0);
//        }
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
        LinkList linkList=new LinkList();
        linkList.addLast(1);
        linkList.addLast(2);
        linkList.addLast(3);
        linkList.addBegin(34);
        linkList.addLast(44);
        System.out.println(linkList.disPlay());
        linkList.removeData(2);
        linkList.removeData(3);
        System.out.println(linkList.disPlay());
        Node node=linkList.get(0);

        linkList.removeNode(node);
        System.out.println(linkList.disPlay());
    }





}

package com.example.demo.DataStructure.otherNum.栈;

import java.io.Serializable;

/**
 * 作者：idea
 * 日期：2018/5/15
 * 描述：链式栈
 */
public class LinkStack<T> implements Serializable{

    public Node top;
    public int size=0;

    class Node<T>{
        public T data;
        public Node next;
        public Node(){

        }
        public Node(T data){
            this.data=data;
        }

    }

    public LinkStack(){
        this.top=new Node();
    }

    public String push(T data){
        Node newNode=new Node(data);
        if(top.next==null){
            top.next=newNode;
        }else{
            Node temp=top.next;
            while(temp.next!=null){
                temp=temp.next;
            }
            temp.next=newNode;
        }
        size++;
        return null;
    }

    public T peek(){
        return (T) top.next.data;
    }

    public T pull() throws Exception {
        if(top.next==null){
            throw  new Exception("stack is null");
        }else{
            Node topDat=top.next;
            top.next=top.next.next;
            size--;
            return (T) topDat.data;
        }
    }



    public String display(){
        StringBuffer result=new StringBuffer("[head=>");
        Node temp=top.next;
        for(int i=0;i<size;i++){
            result.append(temp.data+"=>");
            temp=temp.next;
        }
        result.append(" end]");
        return result.toString();
    }

    public static void main(String[] args) throws Exception {
        LinkStack<Integer> linkStack=new LinkStack<>();
        linkStack.push(1);
        linkStack.push(2);
        linkStack.push(3);
        linkStack.pull();
        System.out.println(linkStack.peek());
        System.out.println(linkStack.display());
    }

}

package com.example.demo.DataStructure.otherNum.循环单向链表;


/**
 * 作者：idea
 * 日期：2018/5/14
 * 描述：单向循环
 */
public class SinglyLoopLink {
    public Node head;
    public Node tail;
    public int size;

    public SinglyLoopLink(){
        this.size=0;
        this.head=new Node(0);
        this.tail=new Node(0);
        head.next=tail;
        tail.next=head;
    }

    class Node {
        Node next;
        int data;
        public Node(){

        }
        public Node(int data){
            this.data=data;
        }
    }

    public void add(int data){
        Node newNode=new Node(data);
        if (head == null) {
            newNode.next=tail;
            head.next=newNode;

        }else{
            Node temp=head;
            while(temp.next!=tail){
                temp=temp.next;
            }
            temp.next=newNode;
            newNode.next=tail;
        }
        size++;
    }

    public String add(int index,int data){
        Node temp=head;
        Node newNode=new Node(data);
        if(index<0||index>size){
            return "数组指标有误";
        }
        for(int i=0;i<size;i++){
            temp=temp.next;

            if(index==0){
                head.next=newNode;
                newNode.next=temp;
                size++;
                break;
            }else if(i==index-1){
                System.out.println("this is "+size);
                newNode.next=temp.next;
                temp.next=newNode;
                size++;
                break;
            }
        }
        return null;
    }

    public String addFirst(int data){
        return add(0,data);
    }

    public String addLast(int data){
        return add(size,data);
    }


    public String disPlay(){
        Node temp=head;
        StringBuffer result=new StringBuffer("[=>head=>");
        for(int i=0;i<size;i++){
            temp=temp.next;
            result.append(temp.data+"=>");

        }
        result.append("tail=>]");
        return result.toString();
    }

    public static void main(String[] args) {
        SinglyLoopLink singlyLoopLink=new SinglyLoopLink();
        singlyLoopLink.add(1);
        singlyLoopLink.add(2);
        singlyLoopLink.add(0,-9);
        singlyLoopLink.add(1,9);
        singlyLoopLink.add(4,10);
        singlyLoopLink.addFirst(-99);
        singlyLoopLink.addLast(100);
        singlyLoopLink.add(0,-155);
//        singlyLoopLink.add(3,10);
        System.out.println(singlyLoopLink.disPlay());
    }

}

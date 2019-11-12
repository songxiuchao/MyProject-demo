package com.example.demo.DataStructure.otherNum.单向链表;

/**
 * 作者：idea
 * 日期：2018/5/9
 * 描述：java实现单向链表
 */
public class MyLink {
    public Node head=null;
    public int size=0;
    class Node{
        Node next=null;
        int data;

        public Node(int data){
            this.data=data;
        }
    }

    //插入数据在链表里面
    public void addData(int data){
        Node node=new Node(data);
        if(head==null){
            head=node;
            return;
        }
        Node temp=head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=node; //最新的那个节点
        size++;
        return;
    }

    //删除第i个节点
    public boolean deleteNode(int index){
        if(index<0){
            System.out.println("请输入正确的index");
            return false;
        }
        if(head==null){
            System.out.println("没有头节点");
            return false;
        } else if(head.next==null){
            System.out.println("已经没有节点可以删除了");
            return false;
        }
        if(index==0){
            head=head.next;
            size--;
            return true;
        }
        if(index>size){
            System.out.println("下标越界了"+size);
            return false;
        }
        int i=1;
        Node preNode=head;
        Node currNode=preNode.next;
        while(currNode!=null){
            if(i==index){
                preNode.next=currNode.next;
                return true;
            }
            preNode=currNode;
            currNode=currNode.next;
            i++;
        }
        size--;
        return false;
    }

    //打印节点的内容
    public String disPlay(){
        if(head!=null&&head.next!=null){
            String content=new String("[");
            Node nextNode=head;
            while(nextNode!=null){
                content+=String.valueOf(nextNode.data+" ");
                nextNode=nextNode.next;
            }
            return content+"]";
        }else{
            return "节点内容为空！";
        }
    }

    public static void main(String[] args) {
        MyLink myLink=new MyLink();
        myLink.addData(2);
        myLink.addData(3);
        myLink.addData(4);
        myLink.addData(5);
        myLink.addData(6);
        myLink.deleteNode(0);
        System.out.println(myLink.disPlay());
    }
}

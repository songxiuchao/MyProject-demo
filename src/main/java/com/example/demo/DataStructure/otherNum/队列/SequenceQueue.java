package com.example.demo.DataStructure.otherNum.队列;

/**
 * 作者：idea
 * 日期：2018/5/26
 * 描述：顺序队列
 */
public class SequenceQueue {
    public static int DEFAULT_SIZE=10;
    public int front=0; //队头
    public int rear=0; //队尾
    public int capacity;
    public Object[] elementData;

    public SequenceQueue(){
        capacity=DEFAULT_SIZE;
        elementData=new Object[capacity];
    }

    public SequenceQueue(Object obj){
        elementData[0]=obj;
        rear++;
    }

    public void add(Object data){
        if(rear>capacity){
            throw new RuntimeException("队列已满");
        }
        this.elementData[rear]=data;
        rear++;
    }

    public Object push(){
        Object result=this.elementData[front];
        for(int i=1;i<=rear;i++){
            Object temp=elementData[i];
            elementData[i-1]=elementData[i];
        }
        elementData[rear]=null;
        rear--;
        return result;
    }

    public void display(){
        for(int j=0;j<elementData.length;j++){
            if(elementData[j]!=null){
                System.out.println(elementData[j]);
            }
        }
    }

    public int length(){
        return elementData.length;
    }


    public static void main(String[] args) {
        SequenceQueue sq=new SequenceQueue();
        sq.add(1);
        sq.add(2);
        sq.add(3);
        sq.add(4);
        sq.push();
        sq.push();
        sq.push();
        sq.display();
    }

}

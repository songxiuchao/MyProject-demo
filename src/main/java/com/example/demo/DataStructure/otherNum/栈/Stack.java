package com.example.demo.DataStructure.otherNum.栈;

import java.util.Arrays;

/**
 * 作者：idea
 * 日期：2018/5/15
 * 描述：栈的结构设计(顺序栈类型)
 */
public class Stack<T> {
    public int top=-1;
    public int capactity=10;
    public int currentSize=0;
    public T[] array;
    public Stack(){
        array= (T[]) new Object[capactity];
    }

    public String push(T data){
        if(currentSize==capactity){
            capactity=(capactity*2+1); //扩容操作
            T[] oldArr=this.array;
            this.array= (T[]) new Object[capactity];
            for (int i=0; i<oldArr.length ; i++) {
                array[i] = oldArr[i];
            }
        }
        if(currentSize<capactity) {
            int index = currentSize;
            this.array[index] = data;
            currentSize++;
            return null;
        }else{
            return "栈溢出";
        }
    }

    public T getTop(){
        T top=this.array[currentSize];
        return top;
    }

    public String pull(){
        this.array[currentSize]=null;
        currentSize--;
        return null;
    }

    public String display(){
        StringBuffer result=new StringBuffer("[bottum:");
        for (T t : array) {
            if(t!=null){
                result.append(t+",");
            }else{
                continue;
            }
        }
        result.append(" head]");
        return result.toString();
    }


    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<100;i++){
            stack.push(i);
        }
        stack.pull();
        stack.pull();
        System.out.println(stack.getTop());
        System.out.println(stack.display());
    }


}

package com.example.demo.DataStructure.otherNum.算法部分.栈;

import java.util.Stack;

/**
 * 查找栈的最小数字
 *
 * @author idea
 * @data 2019/5/28
 */
public class StackGetMin {

    private Stack<Integer> mainStack=new Stack<Integer>();

    private Stack<Integer> minStack=new Stack<Integer>();

    /**
     * 入栈操作
     * @param number
     */
    public void push(Integer number){
        mainStack.push(number);
        if(minStack.isEmpty() || number<=minStack.peek()){
            minStack.push(number);
        }
    }

    /**
     * 出栈操作
     */
    public Integer pop(){
        if(mainStack.empty()){
            throw new RuntimeException("栈为空！");
        }
        return mainStack.pop();
    }

    public Integer getMin(){
        if(mainStack.empty()){
            throw new RuntimeException("栈为空!");
        }
        return minStack.pop();
    }

    public static void main(String[] args) {
        StackGetMin st=new StackGetMin();
        st.push(5);
        st.push(6);
        st.push(3);
        st.push(2);
        st.push(9);
        System.out.println(st.getMin());
        System.out.println(st.getMin());
        System.out.println(st.getMin());
        System.out.println(st.getMin());


    }
}

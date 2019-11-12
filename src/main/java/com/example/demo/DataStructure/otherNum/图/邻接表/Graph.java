package com.example.demo.DataStructure.otherNum.图.邻接表;

import java.util.HashMap;

/**
 * @author idea
 * @data 2019/8/25
 */
public class Graph {

    /**
     * 邻接表元素集合
     */
    private ElementNode[] elementNodes;


    private int size;

    /**
     * 初始化图结构 添加定点的操作
     */
    public void initGraph(String[] vertsArr){
        size = vertsArr.length;
        elementNodes=new ElementNode[size];
        for(int i=0;i<size;i++){
            elementNodes[i]=new ElementNode(vertsArr[i],null);
        }
    }

    /**
     * 添加边
     */
    public void insertEdege(String edege){
        ElementNode elementNode=new ElementNode(edege,null);
        String[] arr = edege.split(",");
        if(arr.length==2){
            String from=arr[0],to=arr[1];
            ElementNode fromNode=getElementNode(from);
            ElementNode nextNode =fromNode.getNext();
            ElementNode newNode=new ElementNode(to,null);
            if(nextNode==null){
                fromNode.setNext(newNode);
                return ;
            }
            while(nextNode.getNext()!=null){
                nextNode=nextNode.getNext();
            }
            nextNode.setNext(newNode);
        }
    }

    /**
     * 获取点
     *
     * @param value
     * @return
     */
    private ElementNode getElementNode(String value){
        for(int i=0;i<size;i++){
            if(elementNodes[i].getValue().equals(value)){
                return elementNodes[i];
            }
        }
        return null;
    }

    public void display(){
        for(int i=0;i<size;i++){
            ElementNode elementNode=elementNodes[i];
            if(elementNode.getNext()==null){
                System.out.print(elementNode.getValue()+"->null"+"\n");
                continue;
            }
            while(elementNode!=null && elementNode.getNext()!=null){
                System.out.print(elementNode.getValue()+"->");
                elementNode=elementNode.getNext();
            }
            System.out.print(elementNode.getValue()+"\n");
        }
    }

    public static void main(String[] args) {
        Graph g=new Graph();
        g.initGraph(new String[]{"A","B","C","D","E","F"});
        g.display();
        g.insertEdege("A,B");
        g.insertEdege("C,A");
        g.insertEdege("A,D");
        g.insertEdege("E,B");
        g.insertEdege("D,E");
        g.insertEdege("D,F");
        g.insertEdege("F,C");
        g.display();
    }

}

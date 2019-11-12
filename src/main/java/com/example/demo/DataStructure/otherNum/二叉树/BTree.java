package com.example.demo.DataStructure.otherNum.二叉树;

import sun.reflect.generics.tree.Tree;

/**
 * 作者：idea
 * 日期：2018/6/30
 * 描述：二叉树
 */
public class BTree {

    public TreeNode root;

    public BTree(int value){
        this.root=new TreeNode(value,null,null);
    }

    private static class TreeNode{
        int value;
        TreeNode leftNode;
        TreeNode rightNode;
        public TreeNode(){}
        public TreeNode(int value,TreeNode leftNode,TreeNode rightNode){
            this.value=value;
            this.leftNode=leftNode;
            this.rightNode=rightNode;
        }
    }

    public TreeNode insert(int data,TreeNode treeNode){
        if(treeNode==null){
            return new TreeNode(data,null,null);
        }
        int compareResult=compareable(data,treeNode.value);
        if(compareResult>0){
            treeNode.rightNode=insert(data,treeNode.rightNode);
        }else if (compareResult<0){
            treeNode.leftNode=insert(data,treeNode.leftNode);
        }else{
            throw  new RuntimeException("there is simple node");
        }
        return treeNode;
    }

    //插入一个节点内容
    public void insertNode(int data){
        TreeNode t=root;
        insert(data,t);
    }

    //前序遍历
    public void iteratorLeftOrder(TreeNode t){
        if(t==null){
            return;
        }
        System.out.print(t.value+" ");
        iteratorLeftOrder(t.leftNode);
        iteratorLeftOrder(t.rightNode);
    }

    //后序遍历
    public void iteratorRightOrder(TreeNode t){
        if(t==null){

            return;
        }
        iteratorRightOrder(t.leftNode);
        iteratorRightOrder(t.rightNode);
        System.out.print(t.value+" ");
    }



    //中序遍历
    public void iteratorMiddleOrder(TreeNode t){
       if(t==null){

           return;
       }
        iteratorMiddleOrder(t.leftNode);
       System.out.print(t.value+" ");
        iteratorMiddleOrder(t.rightNode);
    }

    //如果a>b返回1，否则是-1
    private int compareable(int a,int b){
        if(a>b){
            return 1;
        }else if(a<b){
            return -1;
        }else{
            return 0;
        }

    }


    public int findMax(){
        TreeNode tempNode=root;
        while(tempNode.rightNode!=null){
            tempNode=tempNode.rightNode;
        }
        return tempNode.value;
    }

    public int findMin(TreeNode tempNode){
        while(tempNode.leftNode!=null){
            tempNode=tempNode.leftNode;
        }
        return tempNode.value;
    }

    public int findMin(){
        TreeNode tempNode=root;
        return findMin(root);
    }

    public TreeNode remove(int data,TreeNode t){
        if(t==null){
            return t;
        }
        int compareResult=compareable(data,t.value);
        if(compareResult>0){
            t.rightNode=remove(data,t.rightNode);
        }else if (compareResult<0){
            t.leftNode=remove(data,t.leftNode);
        }else if(t.leftNode!=null&&t.rightNode!=null){
            t.value=findMin(t.rightNode);
            t.rightNode=remove(t.value,t.rightNode);
        }else{
            t=(t.leftNode!=null)?t.leftNode:t.rightNode;
        }
        return t;
    }

    public static void main(String[] args) {
        BTree bt=new BTree(10);
        bt.insertNode(11);
        bt.insertNode(9);
        bt.insertNode(13);
        bt.insertNode(8);
        bt.remove(13,bt.root);
        bt.remove(8,bt.root);
        bt.remove(11,bt.root);
        bt.remove(9,bt.root);
        System.out.println(bt.findMax());
        System.out.println(bt.findMin());
//        bt.iteratorMiddleOrder(bt.root);
//        bt.iteratorLeftOrder(bt.root);
//        bt.iteratorRightOrder(bt.root);
    }

}

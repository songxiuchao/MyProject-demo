package com.example.demo.DataStructure.otherNum.二叉树构建表达式树;

public class Node {
    // 数据
    private  String data;
    // 左子树
    private Node lchild;
    // 右子树
    private Node rchild;

    Node() {
    }

    Node(String data) {
        this.data = data;
    }

    Node(String data, Node lchild, Node rchild) {
        super();
        this.data = data;
        this.lchild = lchild;
        this.rchild = rchild;

    }
    public String getData() {
        return data;
    }
    public Node getLchild() {
        return lchild;
    }
    public Node getRchild() {
        return rchild;
    }

}
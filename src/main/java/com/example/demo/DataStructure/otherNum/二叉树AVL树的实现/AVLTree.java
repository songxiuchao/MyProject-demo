package com.example.demo.DataStructure.otherNum.二叉树AVL树的实现;

//AVL树是高度平衡的而二叉树。它的特点是：AVL树中任何节点的两个子树的高度最大差别为1。

public class AVLTree<T extends Comparable<T>> {
    private AVLTreeNode<T> root;
    static class AVLTreeNode<T extends Comparable<T>>{
        T key;
        int height=0;
        AVLTreeNode<T> leftNode;
        AVLTreeNode<T> rightNode;
        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.key = key;
            this.leftNode = left;
            this.rightNode = right;
            this.height = 0;
        }
        public AVLTreeNode(){}

        @Override
        public String toString() {
            return "AVLTreeNode{" +
                    "key=" + key +
                    ", height=" + height +
                    ", leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }

    private int height(AVLTreeNode<T> tree){
        if(tree!=null){
            return tree.height;
        }
        return 0;
    }

    public int height(){
        return height(root);
    }

    private int max(int a,int b){
        return a>b?a:b;
    }

    //节点旋转LL
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> orgRoot){
        AVLTreeNode<T> leftNode;
        //节点旋转LL类型核心
        leftNode=orgRoot.leftNode;
        orgRoot.leftNode=leftNode.rightNode;
        leftNode.rightNode=orgRoot;

        orgRoot.height=max(height(orgRoot.leftNode),height(orgRoot.rightNode))+1;
        leftNode.height=max(height(leftNode.leftNode),orgRoot.height)+1;
        return leftNode;
    }

    //RR的旋转代码
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> orgRoot){
        AVLTreeNode<T> rightNode;
        rightNode=orgRoot.rightNode;
        orgRoot.rightNode=rightNode.leftNode;
        rightNode.leftNode=orgRoot;
        orgRoot.height=max(height(orgRoot.leftNode),height(orgRoot.rightNode))+1;
        rightNode.height=max(height(rightNode.leftNode),orgRoot.height)+1;
        return rightNode;
    }


    //RL的旋转代码
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> orgRoot){
        orgRoot.rightNode=leftLeftRotation(orgRoot.rightNode);
        return rightRightRotation(orgRoot);
    }

    //LR的旋转代码
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> orgRoot){
        orgRoot.leftNode=rightRightRotation(orgRoot.leftNode);
        return leftLeftRotation(orgRoot);
    }


    //插入代码部分
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree,T key){
        if(tree==null){
            tree=new AVLTreeNode<>(key,null,null); //创建新的节点
        }else{
            int cmp=key.compareTo(tree.key);
            if(cmp<0){
                tree.leftNode=insert(tree.leftNode,key);
                if(height(tree.leftNode)-height(tree.rightNode)==2){ //当两个节点之间的高度差为2的时候需要旋转调整
                    if(key.compareTo(tree.leftNode.key)<0) {
                        tree = leftLeftRotation(tree);
                    } else{
                        tree = leftRightRotation(tree);
                        }
                }
            }else if(cmp>0){
                tree.rightNode=insert(tree.rightNode,key);
                if(height(tree.rightNode)-height(tree.leftNode)==2){ //当两个节点之间的高度差为2的时候需要旋转调整
                    if(key.compareTo(tree.rightNode.key)>0){
                        tree = rightRightRotation(tree);
                    }else{
                        tree = rightLeftRotation(tree);
                    }
                }
            }else{
                System.out.println("添加失败！！");
            }
        }
        int result=max(height(tree.leftNode),height(tree.rightNode))+1;
//        System.out.println(result); 关于树的深度，可以研究一下
        tree.height=result;
        return tree;
    }

    public void insert(T key){
        root=insert(root,key);
    }

    public static void main(String[] args) {
        AVLTree at=new AVLTree();
        at.insert(20);
        at.insert(15);
        at.insert(25);
        at.insert(12);
        at.insert(11);
        at.insert(14);
        at.insert(16);
        at.insert(18);
        System.out.println(at.root.toString());
        System.out.println(at.height());
    }

}

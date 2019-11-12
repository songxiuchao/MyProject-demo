package com.example.demo.DataStructure.otherNum.二叉树;

/**
 * 作者：idea
 * 日期：2018/5/28
 * 描述：
 */
public class BiTree {

    public TreeNode root;

    class TreeNode{
        int value=0;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode(int value) {
            this.value = value;
            this.leftNode = null;
            this.rightNode = null;
        }

        public TreeNode(){
        }
    }

    public BiTree() {
        this.root=new TreeNode(0);
    }

    public void insertNode(int data){
        TreeNode treeNode=new TreeNode(data);
        if(root.value==0){
            this.root=treeNode;
            return;
        }
        TreeNode tempNode=root;
        while(true){
            if(data>tempNode.value){
                if(tempNode.rightNode==null){
                    tempNode.rightNode=treeNode;
                    break;
                }
                tempNode=tempNode.rightNode;
            }else if(data<tempNode.value){
                if(tempNode.leftNode==null){
                    tempNode.leftNode=treeNode;
                    break;
                }
                tempNode=tempNode.leftNode;
            }else if(data==tempNode.value){
                throw new RuntimeException("已经包含相同的数值了");
            }
        }
    }


    public void display(TreeNode treeNode){
        if(treeNode!=null){
            System.out.print(treeNode.value+",");
        }
    }


    public void iteratorFirstRead(TreeNode root){
        if(root!=null){
            iteratorFirstRead(root.rightNode);
            display(root);
            iteratorFirstRead(root.leftNode);
        }
    }

    public static void main(String[] args) {
        BiTree bi=new BiTree();
        bi.insertNode(0);
        bi.insertNode(1);
        bi.insertNode(2);
        bi.insertNode(-1);
        bi.insertNode(-2);
        bi.insertNode(4);
        bi.iteratorFirstRead(bi.root);
    }
}

package com.example.demo.DataStructure.otherNum.二叉树;

import java.util.TreeMap;
import java.util.logging.Level;

/**
 * 作者：idea
 * 日期：2018/5/16
 * 描述：
 */
public class BinaryTree {

    public TreeNode root;

    class TreeNode{
        int value=0;
        TreeNode leftNode;
        TreeNode rightNode;
        public TreeNode(int value){
            this.value=value;
            leftNode=null;
            rightNode=null;
        }

        public TreeNode(){
        }
    }

    public BinaryTree(){
        this.root=new TreeNode(0);
    }

    public void insertNode(int data) throws Exception {
        TreeNode newTreeNode=new TreeNode(data);
        if(root.value==0){
            this.root=newTreeNode;
        }else{
            TreeNode parentNode=root;
            TreeNode temp=root;
            while(true){
                if(data>temp.value){
                    parentNode=temp;
                    if(temp.rightNode==null){
                        parentNode.rightNode=newTreeNode;
                        break;
                    }
                    temp=temp.rightNode;
                }else if(data<temp.value){
                    if(data<temp.value){
                        parentNode=temp;
                        if(temp.leftNode==null){
                            parentNode.leftNode=newTreeNode;
                            break;
                        }
                        temp=temp.leftNode;
                    }
                }else{
                    throw  new Exception("已经有类似的节点了");
                }
            }
        }


    }


    public void readNode(TreeNode treeNode){
        if(treeNode!=null){
            System.out.print(treeNode.value+", ");
        }else{
            return ;
        }
    }

//    前序遍历
    public void iteratorFirstOrder(TreeNode root){
        if(root==null){
            return;
        }
        readNode(root);
        iteratorFirstOrder(root.leftNode);
        iteratorFirstOrder(root.rightNode);
    }

//    后序遍历
    public void iteratorLeftOrder(TreeNode root){
        if(root==null){
            return;
        }
        iteratorLeftOrder(root.rightNode);
        iteratorLeftOrder(root.leftNode);
        readNode(root);
    }

//    中序遍历
    public void iteratorMiddleOrder(TreeNode root){
        if(root==null){
            return;
        }
        iteratorMiddleOrder(root.rightNode);
        readNode(root);
        iteratorMiddleOrder(root.leftNode);
    }

    //获取二叉树的深度
    public int deep(TreeNode root){
        if(root==null){
            return 0;
        }else{
            int left=deep(root.leftNode);
            int right=deep(root.rightNode);
            return 1+Math.max(left,right);
        }
    }

    //获取树的节点数目
    public int getTreeNodeNums(TreeNode root){
        if(root!=null){
            if(root.rightNode==null&&root.leftNode==null){
                return 1;
            }else{
                return getTreeNodeNums(root.rightNode)+getTreeNodeNums(root.leftNode);
            }
        }
        return 0;

    }

    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.insertNode(1);
        binaryTree.insertNode(-4);
        binaryTree.insertNode(3);
        binaryTree.insertNode(-7);
        binaryTree.insertNode(-1);
        binaryTree.insertNode(2);
        binaryTree.insertNode(4);
        binaryTree.insertNode(5);
        System.out.println("前序遍历");
        binaryTree.iteratorFirstOrder(binaryTree.root);
        System.out.println("\n后序遍历");
        binaryTree.iteratorLeftOrder(binaryTree.root);
        System.out.println("\n中序遍历");
        binaryTree.iteratorMiddleOrder(binaryTree.root);
        System.out.println("本树的深度"+binaryTree.deep(binaryTree.root));
        System.out.println("树的叶子节点数量为："+binaryTree.getTreeNodeNums(binaryTree.root));
    }

}

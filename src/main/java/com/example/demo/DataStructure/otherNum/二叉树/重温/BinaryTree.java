package com.example.demo.DataStructure.otherNum.二叉树.重温;

import java.util.ArrayList;

/**
 * @author idea
 * @data 2019/4/30
 */
public class BinaryTree {

    TreeNode root;

    static class TreeNode {

        int value;

        TreeNode left;

        TreeNode right;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

    }

    public void insertNode(int value) throws Exception {
        TreeNode newNode = new TreeNode(value);
        if (root == null) {
            root = newNode;
            return;
        } else {
            TreeNode temp = root;
            TreeNode preparent = root;
            while (true) {
                if (temp.value < value) {
                    preparent = temp;
                    temp = temp.right;
                    if (preparent.right == null) {
                        preparent.right = newNode;
                        return;
                    }
                } else if (value < temp.value) {
                    preparent = temp;
                    temp = temp.left;
                    if (preparent.left == null) {
                        preparent.left = newNode;
                        return;
                    }
                } else {
                    throw new Exception("已经有类似的节点了！");
                }
            }
        }
    }

    public void readNode(TreeNode node) {
        System.out.println(node.value);
    }

    /**
     * 前序遍历
     */
    public void iteratorFirstOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        readNode(root);
        iteratorFirstOrder(root.left);
        iteratorFirstOrder(root.right);
    }


    public void iteratorMidOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        iteratorMidOrder(root.left);
        readNode(root);
        iteratorMidOrder(root.right);
    }

    /**
     * 获取二叉树的深度
     *
     * @param node
     * @return
     */
    public int deep(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = deep(node.left);
        int right = deep(node.right);
        return 1 + Math.max(left, right);
    }

    public void iteratorLastOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        iteratorLastOrder(root.left);
        iteratorLastOrder(root.right);
        readNode(root);
    }

    ArrayList<Integer> list = new ArrayList<>();

    /**
     * 后序遍历
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        preorderTraversal(root);
        return list;
    }

    public void postHandleNode(TreeNode root) {
        if (root == null) {
            return;
        }
        postHandleNode(root.left);
        postHandleNode(root.right);
        saveNode(root);
    }


    public void preorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        saveNode(root);
    }



    public void saveNode(TreeNode root) {
        list.add(root.value);
    }


    /**
     *   5
     * /   \
     * 2   6
     * \    \
     * 3    7
     * \
     * 4
     */
    public static void main(String[] args) throws Exception {
        BinaryTree binaryTree = new BinaryTree();

        binaryTree.insertNode(1);
//        binaryTree.insertNode(2);
//        binaryTree.insertNode(6);
//        binaryTree.insertNode(7);
//        binaryTree.insertNode(3);
//        binaryTree.insertNode(4);
        System.out.println("----");
//
//        System.out.println("前序遍历");
//        binaryTree.iteratorFirstOrder(binaryTree.root);
//        System.out.println("中序遍历");
//        binaryTree.iteratorMidOrder(binaryTree.root);
//
//        System.out.println("后序遍历");
//        binaryTree.iteratorLastOrder(binaryTree.root);
        System.out.println("------");
        binaryTree.preorderTraversal(binaryTree.root);
        for (Integer integer : binaryTree.list) {
            System.out.print(integer + " ");
        }
    }

}

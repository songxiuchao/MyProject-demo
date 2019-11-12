package com.example.demo.DataStructure.otherNum.算法部分.树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * @author idea
 * @data 2019/6/2
 */
public class Demo1 {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 树的深度
     */
    private int deep = 0;

    /**
     * 最小深度
     */
    private List<Integer> deepArr = new ArrayList<>();


    /**
     * 通过层次遍历，遍历第一棵树两个节点都是null的树
     *
     * @param root
     * @return
     */
    public int run(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            depth++;
            for (int i = 0; i < len; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) {
                    return depth;
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return 0;
    }


    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t4.left = t5;
        t3.left = t6;
        System.out.println(demo1.run(t1));
    }
}

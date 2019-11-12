package com.example.demo.DataStructure.otherNum.算法部分.综合升级;


/**
 * 给定一个单链表，其中的元素按升序排序，请将它转化成平衡二叉搜索树（BST）
 *
 * @author idea
 * @data 2019/9/18
 */
public class ListToTree {
    TreeNode root;

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void insert(int val) {
        if (this.root == null) {
            this.root = new TreeNode(val);
            return;
        }
        TreeNode current = root;
        while (true) {
            if (val < current.val) {
                if (current.left == null) {
                    current.left = new TreeNode(val);
                    break;
                }
                current = current.left;
            } else if (val > current.val) {
                if (current.right == null) {
                    current.right = new TreeNode(val);
                    break;
                }
                current = current.right;
            } else if (current.val == val) {
                System.out.println("已经有重复节点了！");
                return;
            }
        }
    }



    public void readTreeNode(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.val + "-->");
        } else {
            System.out.print("null");
        }
    }

    /**
     * 前序遍历 根+左+右
     */
    public void preReadNode(TreeNode node) {
        if (node == null) {
            return;
        }
        readTreeNode(node);
        preReadNode(node.left);
        preReadNode(node.right);
    }

    public TreeNode sortedListToBST(ListNode head) {
        return null;
    }

    /**
     * 左左旋
     *
     * @param root
     * @return
     */
    private TreeNode leftLeftRoate(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode originRoot = root;
        TreeNode newRoot = originRoot.left;
        TreeNode newLeftChild = newRoot.right;
        originRoot.left = newLeftChild;
        newRoot.right = originRoot;
        return newRoot;
    }

    /**
     * 左右旋
     *
     * @param root
     * @return
     */
    private TreeNode rightRightRoate(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode originRoot=root;
        TreeNode newRoot=root.left;
        TreeNode temp=newRoot.right;
        return null;
    }


    public static void main(String[] args) {
        ListToTree listToTree=new ListToTree();
        listToTree.insert(8);
        listToTree.insert(4);
        listToTree.insert(12);
        listToTree.insert(2);
        listToTree.insert(3);
        listToTree.preReadNode(listToTree.root);
        TreeNode newNode = listToTree.leftLeftRoate(listToTree.root);
        System.out.println();
        listToTree.preReadNode(newNode);
    }

}

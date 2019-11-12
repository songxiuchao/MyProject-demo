package com.example.demo.DataStructure.otherNum.算法部分.树;

/**
 * 判断给定的二叉树是否是平衡的
 * 在这个问题中，定义平衡二叉树为每个节点的左右两个子树
 * 高度差的绝对值不超过1的二叉树
 *
 * @author idea
 * @data 2019/9/14
 */


public class BalanceTreeDemo {

    TreeNode root;

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

    /**
     * 判断是否是平衡二叉树 通过层次遍历判别 该方法失败 叶子节点双双为空不能判断
     *
     * @param root
     * @return
     */
    @Deprecated
//    public boolean isBalanced(TreeNode root) {
//        if (root == null) {
//            return false;
//        }
//        TreeNode temp = root;
//        int tempDepth = 0;
//        Queue<TreeNode> queue = new LinkedList<>();
//        queue.offer(root);
//        int count = 0;
//        while (!queue.isEmpty()) {
//            int len = queue.size();
//            tempDepth++;
//            for (int i = 0; i < len; i++) {
//                TreeNode cur = queue.poll();
//                if (cur.left == null && cur.right == null) {
//                    count++;
//                }
//                if (cur.left != null) {
//                    queue.offer(cur.left);
//                }
//                if (cur.right != null) {
//                    queue.offer(cur.right);
//                }
//            }
//        }
//        return count >= 2;
//    }


    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if(Math.abs(getDepth(root.right)-getDepth(root.left))>1){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }


    public int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = getDepth(root.left);
            int right = getDepth(root.right);
            if (left > right) {
                return left+1;
            } else {
                return right+1;
            }
        }
    }


    public static void main(String[] args) {
        BalanceTreeDemo b = new BalanceTreeDemo();
        b.insert(8);
        b.insert(3);
        b.insert(11);
        b.insert(1);
        b.insert(9);
        b.insert(13);
        System.out.println(b.isBalanced(b.root));
    }


}

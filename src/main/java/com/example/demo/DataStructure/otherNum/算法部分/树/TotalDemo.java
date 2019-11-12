package com.example.demo.DataStructure.otherNum.算法部分.树;

/**
 * Given a binary tree containing digits from0-9only, each root-to-leaf path could represent a number.
 * <p>
 * An example is the root-to-leaf path1->2->3which represents the number123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * The root-to-leaf path1->2represents the number12.
 * The root-to-leaf path1->3represents the number13.
 * <p>
 * Return the sum = 12 + 13 =25.
 *
 * @author idea
 * @data 2019/7/14
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class TotalDemo {

    private StringBuilder stb = new StringBuilder();
    public Integer sum=new Integer(0);

    public int sumNumbers(TreeNode root) {
        StringBuilder stb = new StringBuilder();
        findLeafNodeParent(root, stb);
        return sum;
    }

    /**
     * 查询叶子节点
     *
     * @return
     */
    public void findLeafNodeParent(TreeNode root, StringBuilder current) {
        if (root == null) {
            return;
        }
        current.append(root.val);
        if (isLeaf(root)) {
            sum += Integer.parseInt(current + "");
        }
        findLeafNodeParent(root.left, current);
        findLeafNodeParent(root.right, current);
        current.deleteCharAt(current.length() - 1);
    }


    /**
     * 判断是否是叶子节点
     *
     * @param root
     * @return
     */
    public boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);

        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t5.left = t6;

        TotalDemo td = new TotalDemo();
        Integer sum = td.sumNumbers(t1);
        System.out.println(sum);
    }

}

package com.example.demo.DataStructure.otherNum.算法部分.链表;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * <p>
 * 首先看到这种时间复杂度 需要立马想到归并排序，快速排序
 * 基于链表来实现归并排序
 *
 * @author idea
 * @data 2019/4/11
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class MergeSortLinkedTest {

    /**
     * 排序
     *
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode middle = head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            middle = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //截断链表
        middle.next = null;
        ListNode l1 = sortList(head);
        //slow记录了后半段的头结点
        ListNode l2 = sortList(slow);
        return merge(l1, l2);
    }


    /**
     * 基于链表的核心归并排序
     *
     * @param firstNode
     * @param nextNode
     * @return
     */
    private static ListNode merge(ListNode firstNode, ListNode nextNode) {
        ListNode resultList = new ListNode(0);
        ListNode cur = resultList;
        while (firstNode != null && nextNode != null) {
            if (firstNode.val <= nextNode.val) {
                cur.next = firstNode;
                firstNode = firstNode.next;
            } else {
                cur.next = nextNode;
                nextNode = nextNode.next;
            }
            cur = cur.next;
        }
        if (firstNode != null) {
            cur.next = firstNode;
        }
        if (nextNode != null) {
            cur.next = nextNode;
        }
        //head is not reight node
        return resultList.next;
    }


    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(23);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(22);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(16);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        sortList(node);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}

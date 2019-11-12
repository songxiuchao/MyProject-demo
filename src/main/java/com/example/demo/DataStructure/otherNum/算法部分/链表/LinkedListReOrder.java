package com.example.demo.DataStructure.otherNum.算法部分.链表;

/**
 * @author idea
 * @data 2019/4/17
 * @des Given a singly linked list L: L 0→L 1→…→L n-1→L n,
 * reorder it to: L 0→L n →L 1→L n-1→L 2→L n-2→…
 * <p>
 * You must do this in-place without altering the nodes' values.
 * <p>
 * For example,
 * Given{1,2,3,4}, reorder it to{1,4,2,3}.
 */


public class LinkedListReOrder {


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void insertData(int val, ListNode head) {
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new ListNode(val);
    }

    public void display(ListNode head) {
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }


    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }
        // 快满指针找到中间节点
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        // 拆分链表，并反转中间节点之后的链表
        ListNode after = slow.next;
        slow.next = null;
        ListNode pre = null;
        while(after != null){
            ListNode temp = after.next;
            after.next = pre;
            pre = after;
            after = temp;
        }
        // 合并两个链表
        ListNode first = head;
        after = pre;
        while(first != null && after != null){
            ListNode ftemp = first.next;
            ListNode aftemp = after.next;
            first.next = after;
            first = ftemp;
            after.next = first;
            after = aftemp;
        }
    }

    public static void main(String[] args) {
        LinkedListReOrder reOrder = new LinkedListReOrder();
        ListNode l = new ListNode(1);
        reOrder.insertData(412, l);
        reOrder.insertData(22, l);
        reOrder.insertData(155, l);
        reOrder.insertData(22, l);
        reOrder.insertData(41, l);
        reOrder.display(l);
        System.out.println("-------------");
        reOrder.reorderList(l);
        reOrder.display(l);

    }


}

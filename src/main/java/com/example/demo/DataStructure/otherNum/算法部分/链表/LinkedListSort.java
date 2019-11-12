package com.example.demo.DataStructure.otherNum.算法部分.链表;

/**
 * 基于链表进行的插入排序
 *
 * @author idea
 * @data 2019/4/15
 *
 */
public class LinkedListSort {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x,ListNode next) {
            val = x;
            this.next = next;
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

    /**
     * 插排
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newNodeList = new ListNode(Integer.MAX_VALUE);
        ListNode tempNode = head;

        while (tempNode != null) {
            ListNode cur = newNodeList;
            while (cur.next != null && cur.next.val < tempNode.val) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next=new ListNode(tempNode.val,next);
            tempNode=tempNode.next;
        }
        return newNodeList.next;
    }

    public static void main(String[] args) {
        LinkedListSort linkedListSort = new LinkedListSort();
        ListNode l = new ListNode(1);
        linkedListSort.insertData(412, l);
        linkedListSort.insertData(22, l);
        linkedListSort.insertData(155, l);
        linkedListSort.insertData(22, l);
        linkedListSort.insertData(41, l);
        linkedListSort.display(l);
        System.out.println("-------------");
        ListNode newList = linkedListSort.insertionSortList(l);
        linkedListSort.display(newList);

    }
}

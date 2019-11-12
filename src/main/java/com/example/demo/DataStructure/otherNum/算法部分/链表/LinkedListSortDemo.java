package com.example.demo.DataStructure.otherNum.算法部分.链表;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 给出一个链表和一个值x，以x为参照将链表划分成两部分，使所有小于x的节点都位于大于或等于x的节点之前。
 * 两个部分之内的节点之间要保持的原始相对顺序。
 * 例如：
 * 给出1->4->3->2->5->2和x = 3,
 * 返回1->2->2->4->3->5.
 *
 * @author idea
 * @data 2019/8/13
 */


public class LinkedListSortDemo {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if(head==null)
            return null;
        ListNode dummy1=new ListNode(0);
        ListNode dummy2=new ListNode(0);
        ListNode curr1=dummy1;
        ListNode curr2=dummy2;
        while(head!=null){
            if(head.val<x){
                curr1.next=head;
                curr1=curr1.next;
            }else{
                curr2.next=head;
                curr2=curr2.next;
            }
            head=head.next;
        }
        curr2.next=null;
        curr1.next=dummy2.next;
        return dummy1.next;
    }

    public static void main(String[] args) {
        LinkedListSortDemo l = new LinkedListSortDemo();
        ListNode listNode=new ListNode(1);
        ListNode temp=listNode;
        temp.next=new ListNode(4);
        temp=temp.next;

        temp.next=new ListNode(3);
        temp=temp.next;

        temp.next=new ListNode(2);
        temp=temp.next;

        temp.next=new ListNode(5);
        temp=temp.next;

        temp.next=new ListNode(2);
        temp=temp.next;

        ListNode result = l.partition(listNode,3);
        System.out.println("---");

    }

}

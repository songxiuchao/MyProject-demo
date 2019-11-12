package com.example.demo.DataStructure.otherNum.算法部分.链表;

import java.util.List;

/**
 * @author idea
 * @data 2019/4/23
 */
public class CycleListDemo {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if(head==null){
            return null;
        }
        //拿到中间节点的信息
        ListNode meetNode=meetingNode(head);
        if(meetNode==null){
            return null;
        }
        ListNode fast=head;
        ListNode slow=meetNode;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }

    public ListNode meetingNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return slow;
            }
        }
        return null;
    }
}

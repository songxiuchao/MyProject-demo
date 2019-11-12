package com.example.demo.DataStructure.otherNum.算法部分.链表;

import java.awt.*;

/**
 * Given a linked list, determine if it has a cycle in it.
 *
 * Follow up:
 * Can you solve it without using extra space?
 * @author idea
 * @data 2019/5/8
 */

public class LinkedListHasCycle {

     class ListNode {
          int val;
          ListNode next;
          ListNode(int x) {
              val = x;
              next = null;
          }
      }

    public boolean hasCycle(ListNode head) {
        ListNode slow=head;
        ListNode fast=head;

        while (fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                return true;
            }
        }
        if(fast==null || fast.next==null){
            return false;
        }
        return false;
    }


}

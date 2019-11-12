package com.example.demo.DataStructure.otherNum.算法部分.链表;

/**
 * '
 * 随机链表
 * A linked list is given such that each node contains an additional random pointer
 * which could point to any node in the list or null.
 * <p>
 * Return a deep copy of the list.
 *
 * @author idea
 * @data 2019/5/26
 */
public class RandomListNodeDemo {

    class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }


    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode p, copy;
        if (head == null) {
            return null;
        }
        //首先我们需要复制链表的内容
        for (p = head.next; p != null; p = p.next) {
            copy = new RandomListNode(p.label);
            copy.next = p.next;
            p.next = copy;
            p = copy;
        }
        for (p = head; p!=null; p = p.next) {
            copy = p.next;
            copy.random =(p.random!=null?p.random.next:null);
        }
        RandomListNode newHead=copy=head.next;
        for(p=head;p!=null;){
            p=p.next=copy.next;
            copy=copy.next=(p!=null?p.next:null);
        }
        return newHead;
    }
}

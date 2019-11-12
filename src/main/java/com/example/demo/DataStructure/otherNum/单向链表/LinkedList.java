package com.example.demo.DataStructure.otherNum.单向链表;

/**
 * @author idea
 * @data 2018/10/13
 * @des 可以提供单链表的插入，删除，查找操作 目前只存储int类型数据
 */
public class LinkedList {

    private Node head = null;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * 查找相应链表的某个节点
     * <p>
     * 复杂度：T（n）= O(n)
     *
     * @param value
     * @return
     */
    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }


    /**
     * 根据指标来进行节点的搜索
     *
     * @param index
     * @return
     */
    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (pos != index && p != null) {
            p = p.next;
            pos++;
        }
        return p;
    }

    /**
     * 在头部插入
     *
     * @param value
     */
    public void insertIntoHead(int value) {
        Node newNode = new Node(value, null);
        insertIntoHead(newNode);
    }

    private void insertIntoHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * 在某个节点的后边插入
     *
     * @param newNode
     * @param p
     */
    private void insertAfter(Node newNode, Node p) {
        if (p == null) {
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    /**
     * 在某个节点的后边插入 (包含中间插入和尾部插入)
     *
     * @param value
     * @param p
     */
    public void insertAfter(int value, Node p) {
        Node newNode = new Node(value, null);
        insertAfter(newNode, p);
    }

    /**
     * 删除节点根据value值
     *
     * @param value
     */
    public void deleteByValue(int value) {
        Node p = head;
        Node tempNode = null;
        while (p != null && p.getData() != value) {
            tempNode = p;
            p = p.next;
        }
        //防止value不存在
        if (p == null) {
            return;
        }
        //如果说删除的是头节点
        if (tempNode == null) {
            head = head.next;
        } else {
            tempNode.next = p.next;
        }
    }

    /**
     * 打印内容
     */
    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 单链表的反转
     *
     * @param list
     * @return
     */
    public static LinkedList reverse(Node list) {
        Node headNode = null;
        Node previousNode = null;
        Node currentNode = list;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            if (nextNode == null) {
                headNode = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.setHead(headNode);
        return linkedList;
    }

    /**
     * 检测单链表是否成环状
     *
     * @param linkedList
     * @return
     */
    public static boolean checkCircle(LinkedList linkedList) {
        Node head = linkedList.head;
        if (head == null) {
            return false;
        }
        Node fast = head.next;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找中间节点
     *
     * @param linkedList
     * @return
     */
    public static Node findMiddleNode(LinkedList linkedList) {
        Node head = linkedList.head;
        if (head == null) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 有序链表的合并
     *
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static LinkedList mergeSortedLists(Node nodeA, Node nodeB) {
        if (nodeA == null || nodeB == null) {
            return null;
        }
        Node p = nodeA;
        Node q = nodeB;
        Node head = null;
        if (p.getData() < q.getData()) {
            head = p;
            p = p.next;
        } else {
            head = q;
            q = q.next;
        }
        Node r = head;
        while (p != null && q != null) {
            if (p.getData() < q.getData()) {
                r.next = p;
                p = p.next;
            } else {
                r.next = q;
                q = q.next;
            }
            r = r.next;
        }
        if (p != null) {
            r.next = p;
        } else {
            r.next = q;
        }
        LinkedList linkedList = new LinkedList();
        linkedList.setHead(head);
        return linkedList;
    }

    /**
     * 创建环状链表
     *
     * @param len
     */
    public static LinkedList createCircleLinkList(int len) {
        Node head = new Node(0, null);
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        Node node4 = new Node(4, null);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = head;
        LinkedList linkedList = new LinkedList();
        linkedList.setHead(head);
        return linkedList;
    }

    public static Node createNode(int data) {
        return new Node(data, null);
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }
}

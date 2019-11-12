package com.example.demo.DataStructure.otherNum.跳表;

import java.util.Random;

/**
 * @author idea
 * @data 2019/1/9
 */
public class SkipList {

    /**
     * 节点的个数
     */
    private static final int MAX_LEVEL = 16;

    /**
     * 索引的层级
     */
    private int levelCount = 1;

    private Node head = new Node();

    private Random random = new Random();


    public Node find(int value){
        Node p=head;
        for(int i=levelCount-1;i>=0;--i){
            while(p.forwards[i]!=null && p.forwards[i].data<value){
                p=p.forwards[i];
            }
        }
        if(p.forwards[0]!=null && p.forwards[0].data==value){
            return p.forwards[0];
        }else{
            return null;
        }
    }


    /**
     * 随机 level 次，如果是奇数层数 +1，防止伪随机
     *
     * @return
     */
    private int getRandomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public void printAll() {
        Node p = head;
        while (p.forwards[0] != null) {
            System.out.println(p.forwards[0] + " ");
            p = p.forwards[0];
        }
        System.out.println();
    }


    public void insert(int value) {
        int level = getRandomLevel();
        Node newNode = new Node();
        newNode.data = value;
        newNode.maxLevel = level;

        Node update[] = new Node[level];
        for (int i = 0; i < level; ++i) {
            update[i] = head;
        }

        // record every level largest value which smaller than insert value in update[]
        Node p = head;
        for (int i = level - 1; i >= 0; --i) {
            while (p.forwards[i] != null && p.forwards[i].data < value) {
                p = p.forwards[i];
            }
            update[i] = p;// use update save node in search path
        }

        // in search path node next node become new node forwords(next)
        for (int i = 0; i < level; ++i) {
            newNode.forwards[i] = update[i].forwards[i];
            update[i].forwards[i] = newNode;

        }
    }




    public class Node {
        private int data = -1;

        private Node forwards[] = new Node[MAX_LEVEL];

        private int maxLevel = 0;

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxLevel);
            builder.append(" }");

            return builder.toString();
        }

    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        skipList.insert(3);
        skipList.insert(4);
        skipList.insert(7);
        skipList.insert(5);
        skipList.insert(8);
        skipList.insert(24);
        skipList.insert(0);
        skipList.printAll();
    }


}

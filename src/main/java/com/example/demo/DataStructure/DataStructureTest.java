package com.example.demo.DataStructure;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @program: demo
 * @description: 数据结构的测试类
 * @author: xiuchao Song
 * @create: 2019-11-12 16:07
 **/
public class DataStructureTest {

    public static void main(String[] args) {
        LinkedList linkedList=new LinkedList();
//        linkedList.add("20");
//        linkedList.add("3");
//        linkedList.add("40");
//        linkedList.add("5");
//        linkedList.add(0,"30");
//        linkedList.add(1,"1");
//        linkedList.add(2,"10");
//        linkedList.add(3,"2");
//        linkedList.add(4,"20");
        for (int i=0;i<10000000;i++){
            linkedList.add(i);
            if (i==0){
                System.out.println(new Date());
            }

        }

        int size = linkedList.size();
//        Collection collection=new Collection() {
//            @Override
//            public int size() {
//                return 0;
//            }
//
//            @Override
//            public boolean isEmpty() {
//                return false;
//            }
//
//            @Override
//            public boolean contains(Object o) {
//                return false;
//            }
//
//            @Override
//            public Iterator iterator() {
//                return null;
//            }
//
//            @Override
//            public Object[] toArray() {
//                return new Object[0];
//            }
//
//            @Override
//            public Object[] toArray(Object[] a) {
//                return new Object[0];
//            }
//
//            @Override
//            public boolean add(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean remove(Object o) {
//                return false;
//            }
//
//            @Override
//            public boolean containsAll(Collection c) {
//                return false;
//            }
//
//            @Override
//            public boolean addAll(Collection c) {
//                return false;
//            }
//
//            @Override
//            public boolean removeAll(Collection c) {
//                return false;
//            }
//
//            @Override
//            public boolean retainAll(Collection c) {
//                return false;
//            }
//
//            @Override
//            public void clear() {
//
//            }
//        };
//        boolean b = linkedList.addAll(collection);
//        if (!b){
//            System.out.println("======");
//        }
//        linkedList.add("bbbbb");
//        linkedList.add("aaaaa");
//        linkedList.add("ddddd");
//        linkedList.add("ccccc");
        System.out.println(size);
        System.out.println(new Date());
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());
        System.out.println("=========================");
        ArrayList arrayList=new ArrayList();

        for (int i=0;i<10000000;i++){
            arrayList.add(i);
            if (i==0){
                System.out.println(new Date());
            }
        }

        System.out.println(new Date());
    }

}

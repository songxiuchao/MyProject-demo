package com.example.demo.DataStructure.otherNum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    private float f = 1.0f;

    public static void main(String[] args) throws FileNotFoundException {
        java.util.LinkedList linkedList = new java.util.LinkedList();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);
        linkedList.add(5);
        linkedList.add(6);


        Integer result = (Integer) linkedList.get(5);
        System.out.println(result);

        //扩容在1.5倍
        ArrayList arrayList=new ArrayList();
        arrayList.add(1);

        System.out.println(10>>1);
        File file = new File("*****");
        try (FileInputStream fin = new FileInputStream(file)) {
            //执行相关操作
        } catch (Exception e) {
            //异常捕获操作
        }
        System.out.println("this is test end!!");
    }

}

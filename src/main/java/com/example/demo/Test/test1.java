package com.example.demo.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @program: demo
 * @description: 测试反射
 * @author: xiuchao Song
 * @create: 2019-11-16 14:35
 **/
public class test1 {

    public static void test1() throws IllegalAccessException {
        Class<Student> studentClass = Student.class;
        Field[] fields = studentClass.getFields();
        for (Field field : fields) {
            System.out.println("studentClass.getFields()==============");
            System.out.println(field);
        }
        for (Field declaredField : studentClass.getDeclaredFields()) {
            System.out.println("studentClass.getDeclaredFields()==============");
            System.out.println(declaredField);
            String name = declaredField.getName();
            System.out.println(name);
        }

        System.out.println("==================================================");


        Method[] methods = studentClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        test1();
    }

}

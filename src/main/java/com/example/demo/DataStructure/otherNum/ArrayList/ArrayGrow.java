package com.example.demo.DataStructure.otherNum.ArrayList;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：idea
 * 日期：2018/6/16
 * 描述：
 */
public class ArrayGrow {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        List list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(getCapactiy(list));
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(getCapactiy(list));
    }
    public static Integer getCapactiy(List list) throws NoSuchFieldException, IllegalAccessException {
        Integer length=null;
        Class clazz=list.getClass();
        Field field;
        field=clazz.getDeclaredField("elementData");
        field.setAccessible(true);
        Object[] obj= (Object[]) field.get(list);
        length=obj.length;
        return length;
    }
}

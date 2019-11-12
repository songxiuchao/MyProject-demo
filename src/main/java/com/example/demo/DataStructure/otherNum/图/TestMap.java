package com.example.demo.DataStructure.otherNum.图;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TestMap {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Map map = new HashMap(100, 0.8f);
        IntStream.range(0, 101).forEach(i -> map.put(i, i));
        
        Field field = HashMap.class.getDeclaredField("table");
        field.setAccessible(true);
        System.out.println(Array.getLength(field.get(map)));
    }
}
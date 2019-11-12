package com.example.demo.DataStructure.otherNum.栈;

/**
 * 数组栈
 *
 * @author idea
 * @data 2018/10/24
 */
public class ArrayStack {

    /**
     * 数组的大小
     */
    private String[] array;

    /**
     * 数组的个数
     */
    private int count;

    /**
     * 栈的大小
     */
    private int stackSize;

    public ArrayStack(int size) {
        this.array = new String[size];
        this.count = 0;
        this.stackSize = size;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(String value) {
        if (count < stackSize) {
            array[count] = value;
            count++;
        } else {
            expandStack(array, array.length);
        }
    }

    /**
     * 扩容
     *
     * @param oldArray
     * @param size
     */
    private void expandStack(String[] oldArray, int size) {
        String[] newArray = new String[size * 2];
        int oldSize = oldArray.length;
        for (int i = 0; i < oldSize; i++) {
            newArray[i] = oldArray[i];
        }
        count = oldSize;
        stackSize = oldSize * 2;
    }

    /**
     * 出栈
     *
     * @return
     */
    public String pop() {
        if (count == 0) {
            return null;
        }
        String value = this.array[count - 1];
        count--;
        return value;
    }

    /**
     * 打印栈里面的信息
     */
    public void disPlay() {
        for(int i=0;i<count;i++){
            System.out.println(array[i]);
        }
    }
}

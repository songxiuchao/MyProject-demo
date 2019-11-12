package com.example.demo.DataStructure.otherNum.BitMap;

import java.util.Random;

/**
 * @author idea
 * @data 2019/3/30
 */
public class BitMap {

    // Java 中 char 类型占 16bit，也即是 2 个字节
    private char[] bytes;

    private int nbits;

    private static final int SIZE = 100 * 100 * 100 * 100;

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits / 16 + 1];
    }

    public void set(int k) {
        if (k > nbits) {
            return;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        //数字1左移bitindex位
        bytes[byteIndex] |= (1 << bitIndex);
    }

    //两个二进制数字，如果都是1则为1，否则是0
    public boolean get(int k) {
        if (k > nbits) {
            return false;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        int result = (bytes[byteIndex] & (1 << bitIndex));
        return result != 0;
    }

    public static void main(String[] args) {
        Random random = new Random();
        MyBitMap bitSet = new MyBitMap(SIZE);
        int temp;
        for (int i = 0; i < SIZE; i++) {
            temp = random.nextInt(SIZE);
            bitSet.set(temp);
        }
        System.out.println("计算不在这1亿个数字中的数字");
        int count = 0;
        long begin = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            if (!bitSet.contain(i)) {
                count++;
            }
        }
        long end=System.currentTimeMillis();
        System.out.println("不存在的数字有" + count + "个,耗时："+(end-begin));
    }

}

package com.example.demo.DataStructure.otherNum.算法部分.数组;

/**
 * 题目描述：
 * <p>
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * <p>
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 * @author idea
 * @data 2019/6/29
 */
public class Demo {

    public int singleNumber(int[] A) {
        for (int i = 0; i < A.length; i++) {
            int containCount = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[i] == A[j]) {
                    containCount++;
                }
                if (containCount == 2) {
                    break;
                }
            }
            if (containCount == 1) {
                return A[i];
            }
        }
        return -1;
    }



    public int singleNumber2(int[] A) {
        int a = 0 , b = 0;
        for(int c : A){
            int ta,tb;
            ta = a;
            tb = b;
            a = (ta&(~tb)&(~c))|((~ta)&tb&c);
            b = ~ta&((~c&tb)|(~tb&c));
        }
        return a|b;
    }

    public static void main(String[] args) {
        System.out.println(~1);
        Demo demo = new Demo();
        int[] arr = {4, 11, 11, 11};
        int result = demo.singleNumber2(arr);
        System.out.println(result);
    }

}

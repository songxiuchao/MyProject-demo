package com.example.demo.DataStructure.otherNum.算法部分.穷举;

/**
 * 韩信点兵案例
 *
 * 韩信知道部队人数大约1000人左右，具体数字不详，
 * 5人一组剩余1人，7个人一组还剩两个人，
 * 8个人一组还剩3个人，问：这支部队有多少人?
 *
 * @author idea
 * @data 2019/6/8
 */
public class HanxinDemo {

    public static void main(String[] args) {
        for(int i=1;i<=1000;i++){
            if(i%5==1 && i%7==2 && i%8==3){
                System.out.println("这组一共有："+i+"人");
            }
        }
    }
}

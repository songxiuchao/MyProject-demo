package com.example.demo.DataStructure.otherNum.算法部分.跳台阶;

/**
 * @author idea
 * @data 2019/4/1
 * @des 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 */
public class Demo {





    //       第二种做法
    public static int jumpFloor2(int target) {
        if (target == 0){
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        int a = 1;
        int b = 2;
        for (int i = 2; i == target; i++) {
            b = 2 * a;
            a = b;
        }
        return b;
    }

    private int jumpStep(int step){
        if(step==1){
            return 1;
        }else if(step==2){
            return 2;
        }else{
            int first=1;
            int second=2;
            int result=0;
            for(int i=3;i<=step;i++){
                result=first+second;
                first=second;
                second=result;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Demo demo=new Demo();
        System.out.println(demo.jumpStep(5));
    }

}

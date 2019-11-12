package com.example.demo.DataStructure.otherNum.算法部分.跳台阶;

/**
 * @author idea
 * @data 2019/4/1
 */
public class Demo2 {

    public static int jumpStep(int step) {
        if(step<=0){
            return 0;
        }
        return 1<<(step-1);
    }



    // 第一种做法
    public static int jumpFloor1(int target) {
        if (target <= 0) {
            return 0;
        }
        return (int) Math.pow(2, target - 1);
    }

    //       第二种做法
	public static int jumpFloor2(int target) {
		if (target <= 0){
		    return 0;
        }
		if (target == 1) {
		    return 1;
        }
		int a = 1;
		int b = 2;
		for (int i = 2; i <= target; i++) {
			b = 2 * a;
			a = b;
		}
		return b;
	}

    public static void main(String[] args) {
        System.out.println(jumpStep(0));
    }
}

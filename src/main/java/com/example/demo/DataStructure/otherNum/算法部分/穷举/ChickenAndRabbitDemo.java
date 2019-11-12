package com.example.demo.DataStructure.otherNum.算法部分.穷举;

/**
 * 鸡兔同笼案例
 * 问题：有鸡兔共50头，共有脚120只。  问 ：鸡兔分别的数量？
 * @author idea
 * @data 2019/6/8
 */
public class ChickenAndRabbitDemo {

    public static void main(String[] args) {
        for(int i=0;i<=50;i++){
            int rabbit=i;
            int chicken=50-i;
            if((rabbit*4+chicken*2)==120){
                System.out.println("兔子有："+rabbit+"只，鸡有"+chicken+"只 ");
                break;
            }
        }
    }

}

package com.example.demo.DataStructure.bucket;

/**
 * @program: demo
 * @description: 测试类
 * @author: xiuchao Song
 * @create: 2019-11-12 16:23
 **/
public class BucketTest {
    public void test(Bucket bucket){
        if(bucket.getToken()){
            System.out.println("可以访问！");
        }else{
            System.out.println("服务器繁忙，超过限流了！");
        }
    }

    public static void main(String args[]){
        Bucket b = new Bucket(50,1000,"test");
        BucketTest d = new BucketTest();
        for(int i=0; i<300;i++){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(b.getToken()){
                System.out.println("第"+i+"次访问,正常！！");
            }else{
                System.out.println("第"+i+"次访问,限流了！！");
            }
        }
    }

}

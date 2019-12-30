package com.example.demo.Test;

import java.util.concurrent.Semaphore;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-12-30 11:47
 **/
class test2 {
    private static final Semaphore s=new Semaphore(100);

    public test2() { }

    public void  test(){
        if (test2.s.tryAcquire()){

        }
    }
}

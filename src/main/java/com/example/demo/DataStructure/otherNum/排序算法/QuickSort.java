package com.example.demo.DataStructure.otherNum.排序算法;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.text.EditorKit;

/**
 * 作者：idea
 * 日期：2018/6/6
 * 描述：快速排序
 */
public class QuickSort {

    public static int divide(int[] a,int start,int end){
        int base=a[end];
        while(start<end){
            while (start<end && a[start]<=base){
                start++;
            }
            if(a[start]>base){
                int temp=a[start];
                a[start]=base;
                a[end]=temp;
                end--;
            }
            while (start<end && a[end]>=base){
                end--;
            }
            if(a[end]<base){
                int temp=a[start];
                a[start]=base;
                a[end]=temp;
                start++;
            }
        }
        return end;
    }

    /**
     * 排序
     * @param a
     * @param start
     * @param end
     */
    public static void sort(int[] a, int start, int end){
        if(start > end){
            //如果只有一个元素，就不用再排下去了
            return;
        }
        else{
           int resultIndex=divide(a, start, end);
           sort(a,start,resultIndex-1);
           sort(a,resultIndex+1,end);
        }
    }


    public static void disPlay(int[] a){
        for(int x : a){
            System.out.print(x+" ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] a = new int[]{2,7,4,5,10,1,9,3,8,6};
        int[] b = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] c = new int[]{10,9,8,7,6,5,4,3,2,1};
        int[] d = new int[]{1,10,2,9,3,2,4,7,5,6};

        sort(a, 0, a.length-1);
        sort(b, 0, b.length-1);
        sort(c, 0, c.length-1);
        sort(d, 0, d.length-1);

        disPlay(a);
        disPlay(b);
        disPlay(c);
        disPlay(d);


    }
}

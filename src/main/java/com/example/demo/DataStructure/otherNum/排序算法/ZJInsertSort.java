package com.example.demo.DataStructure.otherNum.排序算法;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 作者：idea
 * 日期：2018/6/28
 * 描述：直接插入排序
 */
public class ZJInsertSort {
    public void sort(int[] arr){
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put("idea","asd");
        System.out.println(concurrentHashMap.get("idea"));
        int len=arr.length;
        for(int i=1;i<len;i++){
            if(arr[i]<arr[i-1]){
                int j=i-1;
                int x=arr[i]; //复制为哨兵元素，即存储待排序元素
                arr[i]=arr[i-1];
                while(x<arr[j]){
                    arr[j+1]=arr[j];
                    j--;
                }
                arr[j+1]=x;
            }
            print(arr);
        }
    }

    private void print(int arr[]){
        for(int j=0;j<arr.length;j++){
            System.out.print(arr[j]+" ");
        }
    }

    public static void main(String[] args) {
        ZJInsertSort zi=new ZJInsertSort();
        int[] arr={3,1,5,7,3,4,22,55,2};
        zi.sort(arr);
    }
}

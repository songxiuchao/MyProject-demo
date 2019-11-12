package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 作者：idea
 * 日期：2018/6/17
 * 描述：选择排序
 */
public class SelectSort {

    public static  void selectSort(int[] arr){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }

    public static void display(int[] arr){
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        int[] arr={1,55,66,3,677,11,99,3};
        selectSort(arr);
        display(arr);
    }

}

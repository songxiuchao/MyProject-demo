package com.example.demo.DataStructure.otherNum.排序算法.data_2019_4;

/**
 * @author idea
 * @data 2019/4/2
 * @des 插入排序
 */
public class InsertSort {


    public static void sort(Integer[] arr,int n){
        if(n<=0){
            return ;
        }
        for(int i=1;i<arr.length;i++){
            int value=arr[i];
            int j=i-1;
            for(;j>=0;j--){
                if(arr[j]>value){
                    arr[j+1]=arr[j];
                }else{
                    break;
                }
            }
            arr[j+1]=value;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{99, 41, 55, 2, 66, 2, 7};
        sort(arr,arr.length);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}

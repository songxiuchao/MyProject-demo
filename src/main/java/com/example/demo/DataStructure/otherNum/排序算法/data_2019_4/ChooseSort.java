package com.example.demo.DataStructure.otherNum.排序算法.data_2019_4;

/**
 * @author idea
 * @data 2019/4/2
 * @des 选择排序
 */
public class ChooseSort {

    public static void sort(Integer[] arr){
        int temp;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                        temp=arr[i];
                        arr[i]=arr[j];
                        arr[j]=temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{99, 41, 55, 2, 66, 2, 7};
        ChooseSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}

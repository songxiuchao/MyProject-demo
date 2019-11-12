package com.example.demo.DataStructure.otherNum.排序算法.data_2019_4;

/**
 * @author idea
 * @data 2019/4/2
 * @des 冒泡排序
 */
public class BundleSort {

    //冒泡排序
    private static void sort(Integer[] arr){
        for(int i=0;i<arr.length;i++){
            boolean flag=true;
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]<arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                    flag=false;
                }
            }
            if(flag){
                //已经排序结束了
                break;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr=new Integer[]{};
        BundleSort.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}

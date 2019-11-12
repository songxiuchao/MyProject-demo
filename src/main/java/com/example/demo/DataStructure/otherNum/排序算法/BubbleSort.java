package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 作者：idea
 * 日期：2018/6/6
 * 描述：冒泡排序
 */
public class BubbleSort {


    public void sort(int[] arr){
      for(int i=0;i<arr.length-1;i++){
          for(int j=0;j<arr.length-i-1;j++){
              if(arr[j]>arr[j+1]){
                  int temp=arr[j];
                  arr[j]=arr[j+1];
                  arr[j+1]=temp;
              }
              System.out.print(arr[j]+" ");
          }
          System.out.println("");
      }
    }

    public void sort(int[] arr,boolean showArr){
        if(showArr){
            sort(arr);
            display(arr);
        }else{
            sort(arr);
        }
    }

    public void display(int[] arr){
        System.out.println("冒泡排序： ");
        for (int i : arr) {
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args) {
        int[] arr={12,21,3,4,54,6,7,8,9,10};
        BubbleSort bs=new BubbleSort();
        bs.sort(arr,Boolean.TRUE);
    }
}

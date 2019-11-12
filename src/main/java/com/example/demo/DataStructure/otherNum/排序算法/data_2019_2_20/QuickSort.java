package com.example.demo.DataStructure.otherNum.排序算法.data_2019_2_20;

/**
 * 快速排序
 *
 * @author idea
 * @data 2019/2/21
 */
public class QuickSort {

    public static void sort(int[] arr){
        if(arr.length==0){
            return ;
        }
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr,int p,int r) {
        if(p>=r){
            return;
        }
        int q=quickSort(arr,p,r);
        sort(arr,0,q-1);
        sort(arr,q+1,r);
    }

    private static int quickSort(int[] arr, int p, int r) {
        int prior = arr[r];
        //根据元素的最终位置
        int j = p;
        for (int i = p; i < r; i++) {
            if (arr[i] < prior) {
                swap(arr[i], arr[j]);
                j++;
            }
        }
        //和根据元素做交换
        int temp=arr[j];
        arr[j]=prior;
        arr[r]=temp;
        //返回根据元素的下标
        return j;
    }

    public static void swap(int a, int b) {
        int temp = b;
        b = a;
        a = temp;
    }

    public static void main(String[] args) {
        int[] ar={12,54,23,16,23,6};
        sort(ar);
        for (int i : ar) {
            System.out.print(i+" ");
        }
    }

}

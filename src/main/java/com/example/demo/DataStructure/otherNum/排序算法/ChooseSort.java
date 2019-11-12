package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 选择排序算法
 *
 * @author idea
 * @data 2018/10/28
 */
public class ChooseSort {

    public static Integer[] sort(Integer[] arr) {
        return sort(arr, arr.length);
    }

    /**
     * 选择排序
     *
     * @param arr
     * @param n
     * @return
     */
    private static Integer[] sort(Integer[] arr, int n) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 自己总结的选择排序 个人认为这种排序更加稳定
     *
     * @param arr
     * @param n
     * @return
     */
    private static Integer[] sortB(Integer[] arr, int n) {
        if (n <= 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int index=i;
            int min=arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<min){
                    min=arr[j];
                    index=j;
                }
            }
            arr[index]=arr[i];
            arr[i]=min;
        }
        return arr;
    }



    public static void main(String[] args) {
        Integer[] arr = new Integer[]{99, 41, 55, 2, 66, 2, 7};
        arr = sortB(arr,arr.length);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}

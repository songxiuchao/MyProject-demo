package com.example.demo.DataStructure.otherNum.排序算法.data_2019_2_20;

/**
 * 插入排序
 *
 * @author idea
 * @data 2019/2/20
 */
public class InsertSort {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int temp = arr[i];
            for (; j >= 0; j--) {
                if (arr[j] < temp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 34, 23, 565, 23, 5, 12};
        sort(a);
        for (int i : a) {
            System.out.print(i + " ");
        }
    }


}

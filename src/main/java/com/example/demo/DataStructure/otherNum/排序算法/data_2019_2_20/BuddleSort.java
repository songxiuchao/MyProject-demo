package com.example.demo.DataStructure.otherNum.排序算法.data_2019_2_20;

/**
 * 专门整理的排序案例
 * <p>
 * 冒泡排序
 *
 * @author idea
 * @data 2019/2/20
 */
public class BuddleSort {


    /**
     * 稳定性排序
     * O(n) ~ o(n^2)
     * @param arr
     */
    public static void sort(int[] arr) {
        if (arr.length == 1) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int k = 0; k < arr.length - i - 1; k++) {
                if (arr[k] > arr[k + 1]) {
                    int temp = arr[k];
                    arr[k] = arr[k + 1];
                    arr[k + 1] = temp;
                    flag = true;
                }
            }
            //如果为true
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 34, 2, 65, 3, 52, 78, 3, -2, 99};
        BuddleSort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}

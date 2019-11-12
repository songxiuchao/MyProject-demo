package com.example.demo.DataStructure.otherNum.排序算法.data_2019_2_20;

/**
 * 选择排序
 *
 * @author idea
 * @data 2019/2/20
 */
public class ChooseSort {

    /**
     * 关键点 也是要满足稳定性排序的原则
     *
     * O(n^2)
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int index = i;
            for (int k = i + 1; k < arr.length; k++) {
                if (min > arr[k]) {
                    index = k;
                    min = arr[k];
                }
            }
            arr[index] = arr[i];
            arr[i] = min;
        }
    }


    public static void main(String[] args) {
        int[] arr = {12, 4, 23, 6, 8, 334, 25};
        ChooseSort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

}

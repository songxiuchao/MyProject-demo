package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 冒泡排序 时间复杂度 最好O(n) 最坏情况O(n2)
 *
 * @author idea
 * @data 2018/10/28
 */
public class BundleSort {


    public static Integer[] sort(Integer[] arr) {
        return sort(arr, arr.length);
    }


    /**
     * 冒泡排序
     *
     * @param arr
     * @param n
     * @return
     */
    private static Integer[] sort(Integer[] arr, int n) {
        Integer temp;
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                //已经排序完毕了
                break;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 4, 55, 2, 66, 2, 7};
        arr = sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }

}

package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 希尔排序
 *
 * @author idea
 * @data 2018/10/28
 */
public class ShellSort {

    /**
     * 希尔排序
     * @param arr
     */
    private static Integer[] sort(Integer[] arr) {
        int len = arr.length;
        if (len == 1) {
            return arr;
        }
        int step = len / 2;
        while (step >= 1) {
            for (int i = step; i < len; i++) {
                int value = arr[i];
                int j = i - step;
                for (; j >= 0; j -= step) {
                    if (value < arr[j]) {
                        arr[j+step] = arr[j];
                    } else {
                        break;
                    }
                }
                arr[j+step] = value;
            }

            step = step / 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{99, 41, 55, 2, 66, 2, 7};
        arr = sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }

}

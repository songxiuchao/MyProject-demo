package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 插入排序 平均时间复杂度为O(n2)
 *
 * @author idea
 * @data 2018/10/28
 */
public class InsertSort {

    public static Integer[] sort(Integer[] arr) {
        return sort(arr, arr.length);
    }

    /**
     * 插入排序
     *
     * @param arr
     * @param n
     * @return
     */
    private static Integer[] sort(Integer[] arr, int n) {
        if (n <= 1) {
            return arr;
        }
        for (int i = 1; i < n; i++) {
            int value = arr[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            //注意 这里的j已经减少了一个值了
            arr[j+1]=value;
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

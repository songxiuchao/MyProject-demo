package com.example.demo.DataStructure.otherNum.算法部分.二分查找;

/**
 * 二分查找的一个demo
 * 适用于简单的数据结构，有序的数组，最好是静态类型的数组
 *
 * @author idea
 * @data 2019/2/3
 */
public class Bsearch {

    /**
     * 基于循环的方式来实现二分查询
     *
     * @param arr
     * @param n
     * @param findValue
     * @return
     */
    public static int bsearch(int[] arr, int n, int findValue) {
        int high = n - 1;
        int low = 0;
        int mid = -1;
        while (low <= high) {
            mid = (high + low) / 2;
            if (arr[mid] > findValue) {
                high = mid - 1;
            } else if (arr[mid] == findValue) {
                return 1;
            } else {
                low = mid + 1;
            }

        }
        return -1;
    }

    /**
     * 基于递归方式来实现二分查找
     *
     * @param arr
     * @param low
     * @param high
     * @param findValue
     * @return
     */
    public static int bsearch2(int[] arr, int low, int high, int findValue) {
        int mid = (high + low) / 2;
        if (low == high && arr[low] != findValue) {
            return -1;
        }
        if (arr[mid] == findValue) {
            return 1;
        } else if (arr[mid] > findValue) {
            bsearch2(arr, low, mid - 1, findValue);
        } else {
            bsearch2(arr, mid + 1, high, findValue);
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(5/2);
        int[] arr = {1, 44, 23, 56, 23, 45, 56, 234, 34, 5};
        System.out.println(Bsearch.bsearch2(arr, 0, arr.length - 1, 23));
    }


}

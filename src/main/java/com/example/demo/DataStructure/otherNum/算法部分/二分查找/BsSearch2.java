package com.example.demo.DataStructure.otherNum.算法部分.二分查找;

/**
 * 重复练习--二分查找
 *
 * @author idea
 * @data 2019/5/12
 */
public class BsSearch2 {

    private int search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = (low + high) / 2;
        while (mid != high && mid != low) {
            if (key < arr[mid]) {
                high = mid;
                mid = (low + high) / 2;
            } else if (key > arr[mid]) {
                low = mid;
                mid = (low + high) / 2;
            } else {
                return arr[mid];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BsSearch2 bs = new BsSearch2();
        int result = bs.search(arr, 91);
        System.out.println(result);
    }

}

package com.example.demo.DataStructure.otherNum.排序算法.data_2019_4;

/**
 * @author idea
 * @data 2019/4/3
 * @des 归并排序
 */
public class MergeSort {

    public void sort(int[] arr) {
        sort(arr,0,arr.length-1);
    }

    /**
     * 递归实现归并排序
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     */
    private void sort(int[] arr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        int mid = (endIndex - startIndex) / 2 + startIndex;
        sort(arr, startIndex, mid);
        sort(arr, mid + 1, endIndex);
        merge(arr, startIndex, mid, endIndex);
    }

    /**
     * 合并数组进行排序
     *
     * @param arr
     * @param startIndex
     * @param middleIndex
     * @param endIndex
     */
    private void merge(int[] arr, int startIndex, int middleIndex, int endIndex) {
        int[] temp = new int[endIndex - startIndex + 1];
        int i = startIndex;
        int p = middleIndex + 1;
        int index = 0;
        while (i <= middleIndex && p <= endIndex) {
            if (arr[i] <= arr[p]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[p++];
            }
        }
        int start = i;
        int end = middleIndex;
        if (p <= endIndex) {
            start = p;
            end = endIndex;
        }
        while (start <= end) {
            temp[index++] = arr[start++];
        }
        for (int k = 0; k <= endIndex-startIndex; k++) {
            arr[startIndex+k] = temp[k];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 6, 12, 3, 4,45,12,-2,0};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}

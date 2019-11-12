package com.example.demo.DataStructure.otherNum.排序算法.data_2019_2_20;

/**
 * 归并排序
 *
 * @author idea
 * @data 2019/2/21
 */
public class MergeSort {

    public static void merge(int[] arr, int mid, int start, int end) {
        int i = start;
        int j = mid + 1;
        int[] temp = new int[end - start + 1];
        int index = 0;
        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[index] = arr[i];
                i++;
            } else {
                temp[index] = arr[j];
                j++;
            }
            index++;
        }

        int startIndex = i;
        int endIndex = mid;
        //这里要用j<=end 而不是i==mid，因为当只有两个元素的时候i和mid都为0
        if (j <= end) {
            startIndex = j;
            endIndex = end;
        }
        while (startIndex <= endIndex) {
            temp[index++] = arr[startIndex++];
        }
        for (int n = 0; n <= end - start; n++) {
            arr[start + n] = temp[n];
        }
    }

    public static void sort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int q = startIndex + (endIndex - startIndex) / 2;
        sort(arr, startIndex, q);
        sort(arr, q + 1, endIndex);
        merge(arr, q, startIndex, endIndex);
    }

    public static void main(String[] args) {
        int arr[] = {132, 34,23,545,1,42,6,45};
        sort(arr, 0, arr.length - 1);
//        merge(arr, 0, 0, 1);
        for (int i : arr) {

            System.out.print(i + " ");
        }
    }

}




package com.example.demo.DataStructure.otherNum.排序算法;

/**
 * 归并排序
 *
 * @author idea
 * @data 2018/12/24
 */
public class MergeSort {


    public void mergeSort(int[] arr, int startIndex, int endIndex){
        // 递归终止条件
        if (startIndex>=endIndex) {
            return;
        }

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = startIndex + (endIndex - startIndex)/2;
        // 分治递归
        mergeSort(arr,startIndex, q);
        mergeSort(arr, q+1, endIndex);

        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        merge(arr, startIndex, q, endIndex);
    }

    public void merge(int[] arr, int startIndex, int middleIndex, int endIndex) {
        int[] temp = new int[endIndex - startIndex +1];
        int i = startIndex;
        int p = middleIndex+1;
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
        if (p<=end){
            start=p;
            end=endIndex;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            temp[index++] = arr[start++];
        }
        // 将tmp中的数组拷贝回a[p...r]
        for (i = 0; i <= endIndex-startIndex; ++i) {
            arr[startIndex+i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] numbers={12,3,4,55,6,8,99,10,11,23,56,84};
        MergeSort mergeSort=new MergeSort();
        mergeSort.mergeSort(numbers,0,11);
        for (Integer number : numbers) {
            System.out.print(" "+number);
        }
    }

}

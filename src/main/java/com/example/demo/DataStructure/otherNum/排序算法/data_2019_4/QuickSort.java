package com.example.demo.DataStructure.otherNum.排序算法.data_2019_4;

/**
 * @author idea
 * @data 2019/4/2
 */
public class QuickSort {


    public void sort(Integer[] arr){
        if(arr.length==0){
            return;
        }
        sortInternally(arr,0,arr.length-1);
    }

    /**
     * 递归排序 0~p-1 p+1~r
     * @param arr
     * @param p
     * @param r
     */
    private void sortInternally(Integer[] arr,int p,int r){
        if(p>=r){
            return;
        }
        int q=sort(arr,p,r);
        sortInternally(arr,0,q-1);
        sortInternally(arr,q+1,r);
    }

    /**
     * 核心排序
     *
     * @param arr
     * @param p
     * @param r
     * @return
     */
    private int sort(Integer[] arr, int p, int r) {
        //待比较元素
        int compare = arr[r];
        //比较的开始index
        int i = p;
        int temp;
        for (int j = p; j < r; j++) {
            if (arr[j] < compare) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        arr[r] = arr[i];
        arr[i] = compare;
        return i;
    }

    public static void main(String[] args) {
        Integer[] arr={12,45,2,345,2,2,33};
        QuickSort quickSort=new QuickSort();
        quickSort.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}


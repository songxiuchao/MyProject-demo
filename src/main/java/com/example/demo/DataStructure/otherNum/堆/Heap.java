package com.example.demo.DataStructure.otherNum.堆;

/**
 * @author idea
 * @data 2019/2/18
 */
public class Heap {


    private int[] a;

    private int maxSize;

    private int count;

    public Heap(int size) {
        a = new int[size];
        maxSize = size;
        count = 0;
    }

    public void add(int data) {
        if (count > maxSize) {
            return;
        }
        //堆的第一个元素通常是空的
        count++;
        a[count] = data;
        int i = count;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            //这个是指获取换走元素之后的新下标
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }
        //最底部的右边的节点值传给头部
        a[1] = a[count];
        a[count] = 0;
        count--;

        heapify(a, count, 1);
    }

    //构建一个好的堆，并且进行过相应的优化
    public void buildHeap(int[] arr, int count) {
        if (count == 0) {
            return;
        }
        for (int i = count / 2; i >= 1; --i) {
            heapify(arr, count, i);
        }
    }


    public  void sort(int[] a ,int n){
        buildHeap(a,n);
        int k=n;
        while(k>1){
            swap(a,1,k);
            k--;
            heapify(a,k,1);
        }
    }

    /**
     * 堆化的处理
     *
     * @param a     数组
     * @param total 数组的长度
     * @param j     当前更换节点所在位置的索引(被堆化节点的索引位置)
     */
    private void heapify(int[] a, int total, int j) {
        while (true) {
            int maxPos = j;
            if ((j * 2 <= total) && a[j] < a[j * 2]) {
                maxPos = j * 2;
            }
            if ((j * 2 + 1 <= total) && a[maxPos] < a[j * 2 + 1]) {
                maxPos = j * 2 + 1;
            }
            if (maxPos == j) {
                break;
            }
            //调换节点的位置
            swap(a, j, maxPos);
            j = maxPos;
        }
    }

    public void display() {
        for (int i : a) {
            System.out.print(i);
        }
    }

    private void swap(int[] arr, int n, int k) {
        int temp = arr[n];
        arr[n] = arr[k];
        arr[k] = temp;
    }

    public static void main(String[] args) {
        Heap h = new Heap(10);
        h.add(52);
        h.add(7);
        h.add(328);
        h.add(91);
        h.add(41);
//        int a[]={17,67,5,3,9,8};
        h.sort(h.a,5);
        h.display();
    }
}

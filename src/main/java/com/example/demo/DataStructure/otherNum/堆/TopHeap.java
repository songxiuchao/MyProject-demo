package com.example.demo.DataStructure.otherNum.堆;

/**
 * @author idea
 * @data 2019/2/19
 */
public class TopHeap {

    private int[] arr;

    private int maxSize;

    private int count;

    public TopHeap(int size) {
        this.arr = new int[size];
        this.maxSize = size;
        this.count = 0;
    }


    /**
     * 添加元素
     *
     * @param data
     */
    private void add(int data) {
        if (count > maxSize) {
            return;
        }
        count++;
        arr[count] = data;
        int i = count;
        //向上堆化
        while (i / 2 > 0 && arr[i] > arr[i / 2]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
    }


    /**
     * 删除节点
     *
     * @return
     */
    private int removeNode() {
        if (count < 0) {
            return -1;
        }
        int result = arr[1];
        arr[1] = arr[count];
        count--;
        //向下堆化
        heapify(arr, count, 1);
        return result;
    }


    /**
     * 排序
     *
     * @return
     */
    public int[] sort() {
        int[] result = new int[count];
        int size = count;
        for (int j = 0; j < size; j++) {
            result[j] = removeNode();
        }
        return result;
    }


    /**
     * 堆化
     *
     * @param arr   数组
     * @param total 数组总长度
     * @param i     指堆化的开始索引值
     */
    private void heapify(int[] arr, int total, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= total && arr[i] < arr[i * 2]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= total && arr[maxPos] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            i = maxPos;
        }
    }


    /**
     * 交换数组元素值
     *
     * @param arr
     * @param n
     * @param k
     */
    private void swap(int[] arr, int n, int k) {
        int temp = arr[n];
        arr[n] = arr[k];
        arr[k] = temp;
    }

    public static void main(String[] args) {
        int[] arr=new int[]{12, 3, -45, 234, 123, 12, 55, 33};

        TopHeap topHeap = new TopHeap(12);
        for (int i : arr) {
            topHeap.add(i);
        }
        int[] res = topHeap.sort();
        for (int re : res) {
            System.out.print(re + " ");
        }
    }

}

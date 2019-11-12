package com.example.demo.DataStructure.otherNum.堆;

/**
 * @author idea
 * @data 2019/2/19
 */
public class MyHeap {

    private int[] arr;

    private int maxSize;

    private int count;

    public MyHeap(int size) {
        this.arr = new int[size];
        this.maxSize = size;
        this.count = 0;
    }


    /**
     * 构建大顶堆
     *
     * @param data
     */
    public void add(int data) {
        if (count > maxSize) {
            return;
        }
        count++;
        arr[count] = data;
        int i = count;
        while (i / 2 > 0 && arr[i] > arr[i / 2]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
    }


    public int removeNode() {
        if (count < 0) {
            return -1;
        }
        int result=arr[1];
        arr[1] = arr[count];
        count--;
        heapify(arr, count, 1);
        return result;
    }


    /**
     * 移除头部节点
     */
    public int removeNode(int[] arr,int count) {
        if (count < 0) {
            return -1;
        }
        arr[1] = arr[count];
        count--;
        heapify(arr, count, 1);
        return arr[1];
    }

    public int[] sort() {
        int[] result=new int[count];
        int size=count;
        for(int j=0;j<size;j++){
            result[j]=removeNode();
        }
        return result;
    }


    //堆化处理（每个节点都进行堆化处理）
    public void buildHeap(int[] arr, int count) {
        if (count == 0) {
            return;
        }
        //计算当前需要进行堆化的节点索引
        for (int i = count / 2; i >=1; i--) {
            heapify(arr, count, i);
        }
    }

    /**
     * 堆化 i是指堆化的开始索引值
     */
    public void heapify(int[] arr, int total, int i) {
        while (true) {
            int maxPos = i;
            //这里的等于号非常重要
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

    public void display() {
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private void swap(int[] arr, int n, int k) {
        int temp = arr[n];
        arr[n] = arr[k];
        arr[k] = temp;
    }

    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(10);
        myHeap.add(13);
        myHeap.add(21);
        myHeap.add(2);
        myHeap.add(299);
        myHeap.add(1222);
        myHeap.add(42);
        myHeap.add(33);
        myHeap.display();

        int[] res=myHeap.sort();
        for (int re : res) {
            System.out.println(re+" ");
        }
    }

}

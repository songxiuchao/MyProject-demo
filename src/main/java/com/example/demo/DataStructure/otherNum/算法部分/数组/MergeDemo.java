package com.example.demo.DataStructure.otherNum.算法部分.数组;

/**
 * @author idea
 * @data 2019/8/19
 */
public class MergeDemo {

    /**
     * 合并双数组
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int A[], int m, int B[], int n) {
        int total = A.length + n;
        int[] result = new int[total];
        int p = 0, k = 0, index = 0;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                A[i] = B[i];
            }
            return;
        }
        if(n==0){
            return;
        }
        while (p < m && k < n) {
            if (A[p] < B[k]) {
                result[index] = A[p];
                p++;
            } else if (A[p] >= B[k]) {
                result[index] = B[k];
                k++;
            }
            index++;
        }
        if (p > m) {
            for (; k < n; k++) {
                result[index] = B[k];
                index++;
            }
        } else {
            for (; p < m; p++) {
                result[index] = A[p];
                index++;
            }
        }
        for (int i = 0; i <= index - 1; i++) {
            A[i] = result[i];
        }
    }

    public static void main(String[] args) {
        MergeDemo m = new MergeDemo();
        int a[] = {1};
        int b[] = {2};
        m.merge(a, 1, b, b.length);
    }
}

package com.example.demo.DataStructure.otherNum.算法部分.数组;

import java.util.HashSet;

/**
 * 最长有序序列
 * <p>
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 * <p>
 * For example,
 * Given[100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is[1, 2, 3, 4]. Return its length:4.
 * <p>
 * Your algorithm should run in O(n) complexity.
 *
 * @author idea
 * @data 2019/7/7
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        if (num.length == 1) {
            return 1;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : num) {
            hashSet.add(i);
        }
        int maxCount = 1;
        for (int n : num) {
            if (hashSet.remove(n)) {
                int bigVal = n + 1;
                int smallVal = n - 1;
                int count = 1;
                while (hashSet.remove(smallVal)) {
                    smallVal--;
                    count++;
                }
                while (hashSet.remove(bigVal)) {
                    bigVal++;
                    count++;
                }
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        int result = lcs.longestConsecutive(null);
        System.out.println(result);
    }


}

package com.example.demo.DataStructure.otherNum.算法部分.动态规划;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict,
 * determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * <p>
 * For example, given
 * s ="leetcode",
 * dict =["leet", "code"].
 * <p>
 * Return true because"leetcode"can be segmented as"leet code".
 *
 * @author idea
 * @data 2019/6/19
 */
public class Demo {


    public boolean wordBreak(String s, Set<String> dict) {
        int len = s.length();
        boolean[] arrays = new boolean[len + 1];
        arrays[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (arrays[j] && dict.contains(s.substring(j,i))) {
                    arrays[i]=true;
                    break;
                }
            }
        }
        return arrays[len];
    }


    public static void main(String[] args) {
        Demo demo=new Demo();
        Set<String> set=new HashSet<>();
        set.add("id");
        set.add("eat");
        boolean result=demo.wordBreak("ideat",set);
        System.out.println(result);
    }
}

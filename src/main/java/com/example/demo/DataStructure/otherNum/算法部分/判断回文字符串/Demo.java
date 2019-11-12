package com.example.demo.DataStructure.otherNum.算法部分.判断回文字符串;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author idea
 * @data 2019/7/30
 */
public class Demo {

    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        s = getValidateStr(s);
        int len = s.length();
        char[] chars = s.toCharArray();
        String[] arr = new String[len];
        for (int p=0;p<chars.length;p++) {
            arr[p]= String.valueOf(chars[p]);
        }
        //偶数
        if (len % 2 == 0) {
            int leftIndex = len / 2 - 1;
            int rightIndex = len / 2;
            for (int l = leftIndex, r = rightIndex; l >= 0 && r < len; l--, r++) {
                if (!(arr[l].equals(arr[r]))) {
                    return false;
                }
            }
        } else {
            //奇数
            int midIndex = len / 2;
            for (int i = midIndex - 1, j = midIndex + 1; i >= 0 && j < len; i--, j++) {
                if (!(arr[i].equals(arr[j]))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断是否是合法字符
     *
     * @return
     */
    public String getValidateStr(String str) {
        str=str.toUpperCase();
        String regx = "[A-Za-z0-9]*";
        Matcher m = Pattern.compile(regx).matcher(str);
        StringBuilder stb = new StringBuilder();
        while (m.find()) {
            int start = m.start();
            int end = m.end();
            String matchStr = str.substring(start, end);
            stb.append(matchStr);
        }
        return stb.toString();
    }

    public static void main(String[] args) {
        String test = "aA";
        Demo d = new Demo();
        System.out.println(d.isPalindrome(test));
    }
}

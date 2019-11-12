package com.example.demo.DataStructure.otherNum.算法部分.有穷自动机;

import java.util.Scanner;

/**
 * 模拟dfa的执行原理
 *
 * @author idea
 * @data 2019/6/30
 */
public class DFADemo {

    private String content;

    private String[] status = {"0", "1", "2", "3"};

    private String startStatus;

    private String endStatus;

    public DFADemo(int start, int end) {
        startStatus = status[start];
        endStatus = status[end];
        System.out.println("开始状态:" + startStatus + ", 结束状态:" + endStatus);
    }


    public void setContent(String content) {
        this.content = content;
    }


    public void result() {
        String status = getEndStatus();
        if (status == null) {
            System.out.println("非法输入");
        }
        //已经匹配到了尽头
        if (status.equals(endStatus)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    public String getEndStatus() {
        String status = startStatus;
        char[] tokens = getToken();
        System.out.print("状态机：" + status);
        for (int i = 0; i < tokens.length && status != null; i++) {
            char ch = tokens[i];
            String nextStatus = getNext(status, ch);
            status = nextStatus;
            System.out.print("--->" + nextStatus);
        }
        System.out.println();
        return status;
    }


    public String getNext(String start, char ch) {
        String next;
        switch (start) {
            case "0":
                if (ch == 'a') {
                    next = "1";
                } else if (ch == 'b') {
                    next = "0";
                } else {
                    next = null;
                }
                break;
            case "1":
                if (ch == 'a') {
                    next = "1";
                } else if (ch == 'b') {
                    next = "2";
                } else {
                    next = null;
                }
                break;
            case "2":
                if (ch == 'a') {
                    next = "1";
                } else if (ch == 'b') {
                    next = "3";
                } else {
                    next = null;
                }
                break;
            case "3":
                if (ch == 'a') {
                    next = "1";
                } else if (ch == 'b') {
                    next = "0";
                } else {
                    next = null;
                }
                break;
            default:
                next = null;
                break;
        }
        return next;
    }


    public char[] getToken() {
        int count = 0;
        char[] tokens = this.content.toCharArray();
        return tokens;
    }


    public static void main(String[] args) {
        System.out.println("输入DFA开始状态和结束状态,一行一个:");   //输入0或1或2
        Scanner input = new Scanner(System.in);
        int start = input.nextInt();
        int end = input.nextInt();
        input.nextLine();
        while (true) {
            System.out.println("输入要分析的字符串:");
            String content = input.nextLine();
            DFADemo dfa = new DFADemo(start, end);
            dfa.setContent(content);
            dfa.result();
        }
    }
}

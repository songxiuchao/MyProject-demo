package com.example.demo.DataStructure.otherNum.面试算法题目;

/**
 * @author idea
 * @data 2019/10/13
 */
public class StringTest {

    //默认全部为0
    private static int[] charsIntArr = new int[256];


    /**
     * 转换为字符数组 这里面不考虑转义字符
     *
     * @param iniString
     * @return
     */
    public boolean checkDifferent(String iniString) {
        int len = iniString.length();
        if(len>256) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            int item=iniString.charAt(i);
            System.out.println(item);
            if(charsIntArr[item]==1){
                return false;
            }
            charsIntArr[item]=1;
        }
        return true;
    }


    public static void main(String[] args) {
        StringTest s = new StringTest();
        boolean result = s.checkDifferent("//72");
        System.out.println(result);

    }
}

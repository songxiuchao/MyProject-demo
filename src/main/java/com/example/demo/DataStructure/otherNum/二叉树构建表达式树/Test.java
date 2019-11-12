package com.example.demo.DataStructure.otherNum.二叉树构建表达式树;

public class Test {



    public double stringDouble(String str){
        double sum=0;
        double aa=0;
        double aaa=0;
        int a=0;
        boolean asd = true;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='.'){
                asd=false;
                continue;
            }
            if(asd == true){
                aa=aa*10+(double)(str.charAt(i)-'0');
            }else{
                aaa = aaa*10+(double)(str.charAt(i)-'0');
                a++;
            }
        }
        for(int j=0;j<a;j++){
            aaa=aaa/10;
        }
        sum=aaa+aa;
        return sum;
    }

    public static void main(String[] args) {
//        Interpreter interpreter=new Interpreter();
//        String count="1+1*2";
//        interpreter.set("int",interpreter.eval("("+count+")"));
//        System.out.println(interpreter.get("int"));
        Test t=new Test();
        System.out.println(t.stringDouble(""));
    }
}

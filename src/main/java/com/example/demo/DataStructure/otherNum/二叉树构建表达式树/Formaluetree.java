package com.example.demo.DataStructure.otherNum.二叉树构建表达式树;

import java.util.ArrayList;

/**
 * 表达式二叉树类
 * @author yuxiu
 *
 */
public class Formaluetree {
    private String s="";
    private Node root;     //根节点
    /**
     * 创建表达式二叉树
     * @param str   表达式
     */
    public void creatTree(String str){
        //声明一个数组列表，存放的操作符，加减乘除
        ArrayList<String> operList = new ArrayList<String>();
        //声明一个数组列表，存放节点的数据
        ArrayList<Node> numList = new ArrayList<Node>();
        //第一，辨析出操作符与数据，存放在相应的列表中
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);          //取出字符串的各个字符
            if(ch>='0'&&ch<='9'){
                s+=ch;
            }else{
                numList.add(new Node(s));
                s="";
                operList.add(ch+"");
                
            }
            
        }
        //把最后的数字加入到数字节点中
        numList.add(new Node(s));
        
        while(operList.size()>0){    //第三步，重复第二步，直到操作符取完为止
            //第二，取出前两个数字和一个操作符，组成一个新的数字节点
            Node left = numList.remove(0);
            Node right = numList.remove(0);
            String oper = operList.remove(0);
            
            Node node = new Node(oper,left,right);
            numList.add(0,node);       //将新生的节点作为第一个节点，同时以前index=0的节点变为index=1
            
        }
        //第四步，让根节点等于最后一个节点
        root = numList.get(0);
                        
    }
    /**
     * 输出结点数据
     */
    public void output(){
            output(root);      //从根节点开始遍历
    }
    /**
     * 输出结点数据
     * @param node
     */
    public void output(Node node){
        if(node.getLchild()!=null){       //如果是叶子节点就会终止
            output(node.getLchild());
        }
          //遍历包括先序遍历（根左右）、中序遍历（左根右）、后序遍历（左右根）
        if(node.getRchild()!=null){
            output(node.getRchild());
        }
        System.out.print(node.getData()+" ");


    }
    

    public static void main(String[] args) {
        Formaluetree tree = new Formaluetree();
        tree.creatTree("12377+47888-90009");//创建表达式的二叉树
        tree.output();//输出验证

    }

}
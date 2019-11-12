package com.example.demo.DataStructure.otherNum.二叉树构建表达式树.简单表达式树_包含加减乘除;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FormalueTree {

    private Node root;
    private String s="";
    private double countResult=0;
    private List<Node> nodeList=new ArrayList<>();
    private List<String> oprsList=new ArrayList<>();
    private static class Node{
        String data;
        Node leftNode;
        Node rightNode;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public void setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public void setRightNode(Node rightNode) {
            this.rightNode = rightNode;
        }

        public Node(String item){
            data=item;
        }

        public Node(String data, Node leftNode, Node rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data='" + data + '\'' +
                    ", leftNode=" + leftNode +
                    ", rightNode=" + rightNode +
                    '}';
        }
    }

    public String createTree(String content){
        if(content.length()>0){
            for(int i=0;i<content.length();i++){
                char item=content.charAt(i);
                if(!isNumber(item)){  //识别为操作字符内容
                    oprsList.add(item+"");
                    Node node=new Node(s);
                    nodeList.add(node);
                    s="";
                }else{//识别为数字内容
                    s+=item;
                }
            }
            nodeList.add(new Node(s));//添加最后的一个数字内容
            //一步步添加节点
            while(oprsList.size()>0){
                Node left=nodeList.remove(0);
                Node right=nodeList.remove(0);
                String oper=oprsList.remove(0);
                Node node=new Node(oper,left,right);
                nodeList.add(0,node);
            }
            root=nodeList.get(0);
         return null;
        }else{
            throw new RuntimeException("表达式内容没有清空");
        }
    }

    public void printNode(){
        printNode(root);
    }

    private void printNode(Node node){
        if(node.getLeftNode()!=null){
            printNode(node.leftNode);
        }
        System.out.println(node.data);
        if(node.getRightNode()!=null){
            printNode(node.rightNode);
        }
    }



    private boolean isNumber(char item){
        return (item>='0'&&item<='9')?true:false;
    }

    //只限于用加减的表达式树
    private double countResult(Node root){
        double result=0;
        Node lNode=root.getLeftNode();
        Node rNode=root.getRightNode();
        if(root.rightNode!=null&&root.leftNode!=null) {
            double rightValue = countResult(root.rightNode);
            double leftValue = countResult(root.leftNode);
            if (root.data.equals("+")) {
                result = leftValue + rightValue;
            } else if (root.data.equals("-")) {
                result = leftValue - rightValue;
            }else if(root.data.equals("*")){
                result=leftValue*rightValue;
            }else if(root.data.equals("/")){
                result=leftValue/rightValue;
            }
        }else{
            result=Double.parseDouble(root.data);
        }
        return result;
    }

    public double countResult(){
        return countResult(root);
    }


    public static void main(String[] args) {
        FormalueTree ft=new FormalueTree();
        ft.createTree("1+1*2*2");
        ft.printNode();
        System.out.println(ft.countResult());
    }
}

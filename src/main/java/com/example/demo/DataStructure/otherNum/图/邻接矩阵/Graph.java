package com.example.demo.DataStructure.otherNum.图.邻接矩阵;

/**
 * @author idea
 * @data 2019/8/25
 */
public class Graph {

    /**
     * 二维矩阵
     */
    public int[][] graph;

    /**
     * 矩阵里面的点集合 A,B,C,D,E,F,G
     */
    public String[] nodeArr;

    private int height;

    private int width;

    public void init(String[] nodeArr){
        this.nodeArr=nodeArr;
        int total=nodeArr.length;
        init(total,total);
    }

    /**
     * 初始化图的长和宽
     *
     * @param height
     * @param width
     */
    private void init(int height, int width) {
        graph = new int[height][width];
        this.height = height;
        this.width = width;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                graph[i][j] = 0;
            }
        }
    }

    /**
     * 打印出图的内部结构
     */
    public void displayGraph() {
        System.out.println("图的高度：" + height + " 图的宽度：" + width);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(graph[i][j]+" ");
            }
            System.out.println("");
        }
    }

    /**
     * 添加矩阵元素
     */
    public void insertItem(String vert) {
        String[] verts=vert.split(",");
        if(verts.length==2){
            String xStr=verts[0];
            String yStr=verts[1];
            if(xStr.equals(yStr)){
                return;
            }
            int x=0,y=0;
            for(int i=0;i<nodeArr.length;i++){
                if(nodeArr[i].equals(xStr)){
                   x=i;
                }
                if(nodeArr[i].equals(yStr)){
                    y=i;
                }
            }
            graph[x][y]=1;
            graph[y][x]=1;
        }
    }


    public static void main(String[] args) {
        Graph g=new Graph();
        g.init(new String[]{"A"});
        g.displayGraph();
        g.insertItem("A,B");
        g.insertItem("B,C");
        g.insertItem("C,D");
        g.insertItem("D,E");
        g.displayGraph();
    }

}

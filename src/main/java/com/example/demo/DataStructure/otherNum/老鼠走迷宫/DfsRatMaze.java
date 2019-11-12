package com.example.demo.DataStructure.otherNum.老鼠走迷宫;

import com.example.demo.DataStructure.otherNum.算法部分.老鼠走迷宫.DfsRatMaze2;

import java.util.LinkedList;

/**
 * 作者：idea
 * 日期：2018/5/21
 * 描述：
 */
//走迷宫这类型的算法题目的首要思路是，先打印迷宫，然后在用穷举的思路去思考
public class DfsRatMaze {

    public final static int END_X=3;  //出口处的x坐标
    public final static int END_Y=3;  //出口处的Y坐标
    public final static int WIDTH=5;
    public final static int HEIGHT=4;
    public   int[][] maze=new int[WIDTH][HEIGHT];
    public   int[][] mark=new int[WIDTH][HEIGHT];
    public LinkedList<Integer> map=new LinkedList<>();
    int min=Integer.MAX_VALUE;
    //开始迷宫的穷举算法
    public void dfs(int startX,int startY,int step){
        int[][] next=new int[][]{
                {1,0},{0,1},{-1,0},{0,-1}
        };
        int nextX,nextY;
        int posible;
        if(startX==END_X&&startY==END_Y){
            if(step<min){
                min=step;
            }
            for (int i=map.size()-1;i>=0;i=i-2) {
                nextX=map.get(i);
                nextY=map.get(i-1);
                System.out.print("["+nextX+","+nextY+"]");
                if(i!=1){
                    System.out.print("-->");
                }
            }
            System.out.println();
            return;
        }
        for(posible=0;posible<next.length;posible++){
            nextX= startX+next[posible][0];
            nextY= startY+next[posible][1];
            if(nextX<0||nextY<0||nextX>=WIDTH||nextY>=HEIGHT){ //超出了迷宫边界值
                continue ;
            }
//            System.out.println(nextX+" "+nextY);
            if(maze[nextX][nextY]==0 && mark[nextX][nextY] == 0){
                map.push(nextX);
                map.push(nextY);
                mark[nextX][nextY] = 1;
                dfs(nextX,nextY,step+1);
                mark[nextX][nextY] = 0;
//                break;
                map.pop();
                map.pop();
            }
        }
    }

    //首先需要初始化一下迷宫
    public void initMaze(){
        maze=new int[WIDTH][HEIGHT];
        mark=new int[WIDTH][HEIGHT];
        //设置相应的障碍点
        maze[2][0]=1;
        maze[1][2]=1;
        maze[2][2]=1;
        maze[3][2]=1;
        mark[0][0]=1;//标志已经走过的格子
        //打印迷宫走过的记录
        for(int y=0;y<HEIGHT;y++){
            for(int x=0;x<WIDTH;x++){
                if(x==END_X&&y==END_Y){ //打印出口的位置
                    System.out.print("!");
                }else if(maze[x][y]==1){
                    System.out.print(maze[x][y]);
                }else{
                    System.out.print(maze[x][y]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DfsRatMaze2 dfsRatMaze=new DfsRatMaze2();
        dfsRatMaze.initMaze();
        dfsRatMaze.dfs(0,0,0);
//        for (Integer integer : dfsRatMaze.map) {
//            System.out.println(integer+" ");
//        }

    }



}

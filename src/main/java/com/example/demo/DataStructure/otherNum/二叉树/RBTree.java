package com.example.demo.DataStructure.otherNum.二叉树;

public class RBTree<T extends Comparable<T>> {
    public final static boolean RED=true;
    public final static boolean BLACK=true;
    public RBNode<T> root;

    class RBNode<T>{
        RBNode root;
        boolean color;
        T key;
        RBNode<T> left;
        RBNode<T> right;
        RBNode<T> parent;

        RBNode(T key,boolean color,RBNode<T> parent,RBNode<T> left,RBNode<T> right){
            this.key=key;
            this.color=color;
            this.left=left;
            this.right=right;
            this.parent=parent;
        }
        public T getKey(){
            return key;
        }

        public String toString(){
            return "" + key + (this.color == RED? "R" : "B");
        }
    }



    /*************对红黑树节点x进行左旋操作 ******************/
    /*
     * 左旋示意图：对节点x进行左旋
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x){
        RBNode<T> y=x.right;
        x.right=y.left;
        if(y.left!=null){
            y.left.parent=x;
        }
        y.parent=x.parent;
        if(x.parent==null){
            this.root=y;
        }else{
            if(x==x.parent.left){
                x.parent.left=y;
            }else{
                x.parent.right=y;
            }
        }
        x.parent=y;
        y.left=x;
    }

    /*************对红黑树节点y进行右旋操作 ******************/
    /*
     * 左旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y){
        RBNode<T> x=y.left;
        y.left=x.right;
        if(x.right!=null){
            x.right.parent=y;
        }
        x.parent=y.parent;
        if(y.parent==null){
            this.root=x;
        }else{
            if(y==y.parent.left){
                y.parent.left=x;
            }else{
                y.parent.right=x;
            }
        }
        x.right=y;
        y.parent=x;
    }

    public void insert(T key){
        RBNode<T> node=new RBNode<T>(key,RED,null,null,null);
        if(node!=null){
            insert(node);
        }
    }

    public void insert(RBNode<T> node){
        RBNode<T> current = null; //表示最后node的父节点
        RBNode<T> x = this.root; //用来向下搜索用的
        while(x!=null){
            current=x;
            if(gotoRight(node,x)){
                x.right=node;
            }else{
                x.left=node;
            }
        }
        node.parent=current;
        if(current!=null){
            if(gotoRight(node,current)){
                current.right=node;
            }else{
                current.left=node;
            }
        }else{
            this.root=node;
        }

    }

    //判断去往那颗子树
    public boolean gotoRight(RBNode<T> node,RBNode<T> currentNode){
        if(node.key.compareTo(currentNode.key)>0){
            return true;
        }else{
            return false;
        }
    }






}
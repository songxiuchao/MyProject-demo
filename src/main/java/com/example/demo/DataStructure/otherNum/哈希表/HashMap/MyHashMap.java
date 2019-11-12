package com.example.demo.DataStructure.otherNum.哈希表.HashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 作者：idea
 * 日期：2018/6/25
 * 描述：
 */
public class MyHashMap<K,V> implements MyMap<K,V> {


    private static final int DEAFULT_SIZE=1<<4;  //16
    private static final float DEFAULT_LOAD_SIZE=0.76f; //负载因子
    private int deaultInitSize;  //默认大小
    private float deaultLoadSize; //默认负载因子
    private int entrySize; //默认的entry数组数量

    private Entry<K,V>[] table=null;

    public MyHashMap(){
        this(DEAFULT_SIZE,DEFAULT_LOAD_SIZE);
    }

    //采用了门面模式
    //仔细观察下，你会发现，其实这里使用到了“门面模式”。这里的2个构造方法其实指向的是同一个，但是对外却暴露了2个“门面”！
    public MyHashMap(int deaultInitSize,float deaultLoadSize){
        this.deaultInitSize=deaultInitSize;
        this.deaultLoadSize=deaultLoadSize;
        table=new Entry[this.deaultInitSize];
    }

    @Override
    public V get(K key) {
        int index=hash(key)&(deaultInitSize-1);
        if(table[index]==null){
            return null;
        }else{
            Entry<K,V> e=table[index];
            do{
                if(e.getKey().equals(key)){
                    return e.getValue();
                }
                e=e.next;
            }while(e!=null);
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if(entrySize>=deaultInitSize*deaultLoadSize){
            //扩容操作
            resize(deaultInitSize*2);
        }
        int index=hash(key)&(deaultInitSize-1);
        if(table[index]==null){
            table[index]=new Entry<K,V>(key,value,null);
            entrySize++;
        }else{
            Entry<K,V> tempEntry=table[index];
            Entry<K,V> e=tempEntry;
            while(e!=null){
                if(key ==e.getKey()||e.getKey().equals(key)){
                    e.setValue(value);
                    break;
                }
                e=e.next;
            }
            //这里面需要联想一下链表那边的引用，next指示了原有的链表地址，所以第三个参数是tempentry
            table[index]=new Entry<K,V>(key,value,tempEntry);
            entrySize++;
        }
        return value;
    }



    //自定义的hash算法
    private int hash(Object key){
        int hashcode= Objects.hashCode(key);
        hashcode^=(hashcode>>>20)^(hashcode>>>12);
        return hashcode^(hashcode>>>7)^(hashcode>>>4);
    }

    //重新扩容，核心在rehash里面
    private void resize(int i){
        Entry[] newTable=new Entry[i];
        this.deaultInitSize=i;
        this.entrySize=0;
        System.out.println("newTable size:--------->"+newTable.length);
        rehash(newTable);

    }

    //扩容算法的核心部分
    private void rehash(Entry<K,V>[] newTable){
        List<Entry<K,V>> entryList=new ArrayList<Entry<K,V>>();
        for(Entry<K,V> e:table){
            while(e!=null) {
                entryList.add(e);
                e=e.next;
            }
        }
        if(newTable.length>0){
            table=newTable;
        }
        for (Entry<K,V>  entry : entryList) {
            put(entry.getKey(),entry.getValue());
        }
    }

}

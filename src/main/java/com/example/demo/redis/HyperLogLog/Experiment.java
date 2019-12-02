package com.example.demo.redis.HyperLogLog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-12-02 14:32
 **/
class Experiment{
    /* 桶数 */
    private int bucketCount;
    /* 随机值数量 */
    private int valueCount;
    private Bucket[] buckets;

    public Experiment(int bucketCount,  int valueCount){
        this.bucketCount = bucketCount;
        this.valueCount = valueCount;
        this.buckets = new Bucket[bucketCount];
        for (int i=0; i<bucketCount; i++){
            buckets[i] = new Bucket(32);
        }
    }

    /**
     * 将valueCount个随机值散列到各个桶中
     */
    public void work() {
        for (int i = 0; i < this.valueCount; i++) {
            long m = ThreadLocalRandom.current().nextLong(1L << 32);
            Bucket bucket = buckets[(int) (((m & 0xfff0000) >> 16) % bucketCount)];
            bucket.lowZero(m);
        }
    }

    /**
     * 使用调和平均计算低位连续0的最大数量的平均值
     * @return
     */
    public double caculate(){
        double totalBit = 0.0;
        for (int i=0; i<bucketCount; i++){
            totalBit += 1.0 / (double)this.buckets[i].getMaxZero();
        }
        double averageBit = (double)bucketCount / totalBit;

        return Math.pow(2, averageBit) * bucketCount;
    }

    public static void main(String[] args) {
        for (int i = 100000; i < 1000000; i += 100000){
            Experiment e = new Experiment(1024, i);
            e.work();

            double num = e.caculate();
            System.out.printf("实际数量：%d,计算数量：%.2f，错误率：%.2f", i, num, Math.abs(num-i)/i);
            System.out.println();
        }
    }
}

/**
 * 桶
 */
class Bucket{
    private int maxZero;
    private int bit;

    public Bucket(int bit){
        this.bit = bit;
    }

    /**
     * 计算低位连续0的最大数量
     */
    public void lowZero(long value){
        int i = 1;
        for (; i<bit; i++){
            if (value >> i << i != value){
                break;
            }
        }
        maxZero = maxZero>i-1 ? maxZero:i-1;
    }

    public int getMaxZero() {
        return maxZero;
    }
}
package com.example.demo.algorithm.BloomFilter;

import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisBloomFilter{

    private RedisBloomFilterStrategy redisBloomFilterStrategy ;

    private RedisBitMap redisBitMap ;

    private long expectedInsertions;

    private double fpp;

    private String bloomFilterName;

    private StringRedisTemplate stringRedisTemplate;

    private int numOfHashFunctions;

    private long bits;

    public RedisBloomFilter(long expectedInsertions, double fpp, String bloomFilterName, StringRedisTemplate stringRedisTemplate) {
        this.expectedInsertions = expectedInsertions;
        this.fpp = fpp;
        this.bloomFilterName = bloomFilterName;
        this.stringRedisTemplate = stringRedisTemplate;
        this.redisBloomFilterStrategy = new RedisBloomFilterStrategy();
        this.bits = optimalNumOfBits(expectedInsertions ,fpp);
        this.numOfHashFunctions = optimalNumOfHashFunctions(expectedInsertions ,bits);
        this.redisBitMap = new RedisBitMap(stringRedisTemplate ,bloomFilterName ,bits);
    }

    public Boolean put(String value){
        return this.redisBloomFilterStrategy.put(value ,numOfHashFunctions ,redisBitMap);
    }

    public Boolean mightContain(String value){
        return this.redisBloomFilterStrategy.mightContain(value ,numOfHashFunctions ,redisBitMap);
    }

    /**
     * 计算 哈希函数个数
     */
    int optimalNumOfHashFunctions(long expectedInsertions, long numbits) {
        // (m / n) * log(2), but avoid truncation due to division!
        return Math.max(1, (int) Math.round((double)numbits / expectedInsertions * Math.log(2)));
    }

    /**
     * 计算所需空间大小
     */
    long optimalNumOfBits(long expectedInsertions, double fpp) {
        if (fpp == 0) {
            fpp = Double.MIN_VALUE;
        }
        return (long) (-expectedInsertions * Math.log(fpp) / (Math.log(2) * Math.log(2)));
    }
}

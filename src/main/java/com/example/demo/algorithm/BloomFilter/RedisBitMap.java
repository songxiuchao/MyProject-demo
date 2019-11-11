package com.example.demo.algorithm.BloomFilter;

import org.springframework.data.redis.core.StringRedisTemplate;

/**
 *
 */
public class RedisBitMap{

    private static final String BASE_KEY = "bloomfilter";

    private StringRedisTemplate redisTemplate;

    private String bloomFilterName = "";

    public RedisBitMap(StringRedisTemplate redisTemplate, String bloomFilterName , long bits) {
        this.redisTemplate = redisTemplate;
        this.bloomFilterName = bloomFilterName;
        redisTemplate.opsForValue().setBit(bloomFilterName ,bits-1 ,false);
    }

    public  Boolean set(long bitIndex){
        if (get(bitIndex)){
            return false;
        }

        return redisTemplate.opsForValue().setBit(bloomFilterName ,bitIndex ,true);
    }

    public Boolean get(long bitIndex){
        return redisTemplate.opsForValue().getBit(bloomFilterName, bitIndex);
    }

    /**
     * 当前array总bits
     */
    public Long bitSize(){
        return redisTemplate.opsForValue().size(bloomFilterName);
    }
}

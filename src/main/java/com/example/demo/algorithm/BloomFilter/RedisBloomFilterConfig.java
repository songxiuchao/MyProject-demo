package com.example.demo.algorithm.BloomFilter;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
@ConfigurationProperties(prefix = "filter")
public class RedisBloomFilterConfig {

    private String name;

    private Long expectedInsertions;

    private Double fpp;

    @Bean
    public RedisBloomFilter redisBloomFilter(StringRedisTemplate stringRedisTemplate){
        RedisBloomFilter redisBloomFilter = new RedisBloomFilter(expectedInsertions ,fpp ,name ,stringRedisTemplate);
        return redisBloomFilter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExpectedInsertions() {
        return expectedInsertions;
    }

    public void setExpectedInsertions(Long expectedInsertions) {
        this.expectedInsertions = expectedInsertions;
    }

    public Double getFpp() {
        return fpp;
    }

    public void setFpp(Double fpp) {
        this.fpp = fpp;
    }
}

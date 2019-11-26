package com.example.demo.algorithm.BloomFilter;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 布隆过滤器是由一个很长的bit数组和一系列哈希函数组成的。
 * 数组的每个元素都只占1bit空间，并且每个元素只能为0或1。
 * 布隆过滤器还拥有k个哈希函数，当一个元素加入布隆过滤器时，会使用k个哈希函数对其进行k次计算,得到k个哈希值，并且根据得到的哈希值，在维数组中把对应下标的值置位1。
 * 判断某个数是否在布隆过滤器中，就对该元素进行k次哈希计算，得到的值在位数组中判断每个元素是否都为1，如果每个元素都为1，就说明这个值在布隆过滤器中。
 * 使用场景：
 * 网页爬虫对URL的去重，避免爬去相同的URL地址。
 * 垃圾邮件过滤，从数十亿个垃圾邮件列表中判断某邮箱是否是杀垃圾邮箱。
 * 解决数据库缓存击穿，黑客攻击服务器时，会构建大量不存在于缓存中的key向服务器发起请求，在数据量足够大的时候，频繁的数据库查询会导致挂机。
 * 秒杀系统，查看用户是否重复购买。
 */

@Data
//@Configuration   //用的时候开启
//@ConfigurationProperties(prefix = "filter")   //用的时候开启
public class RedisBloomFilterConfig {

    private String name;

    private Long expectedInsertions;

    private Double fpp;

    @Bean
    public RedisBloomFilter redisBloomFilter(StringRedisTemplate stringRedisTemplate){
        RedisBloomFilter redisBloomFilter = new RedisBloomFilter(expectedInsertions ,fpp ,name ,stringRedisTemplate);
        return redisBloomFilter;
    }
}

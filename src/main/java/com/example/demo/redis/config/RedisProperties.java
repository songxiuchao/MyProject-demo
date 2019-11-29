package com.example.demo.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author guowei
 * @version 1.0
 * @description redis配置文件
 * @date 2019/4/2 13:50
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
public class RedisProperties {
    @Value("${spring.redis.cluster.nodes}")
    private String nodes;
    private String password;
    private int    timeout;

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}

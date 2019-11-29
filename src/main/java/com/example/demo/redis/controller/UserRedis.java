package com.example.demo.redis.controller;

/**
 * @program: demo
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-26 11:03
 **/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRedis implements Serializable{
    private String name;
    private Integer age;
}

package com.example.demo.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @description: 同学类
 * @author: xiuchao Song
 * @create: 2019-11-16 14:35
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String name;
    private String age;
}

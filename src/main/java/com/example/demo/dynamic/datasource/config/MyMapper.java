package com.example.demo.dynamic.datasource.config;

import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author sxc
 * @version 1.0
 * description: 通用 mapper 自定义 mapper 文件
 * @date 2020/5/5 9:00
 */
@RegisterMapper
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}


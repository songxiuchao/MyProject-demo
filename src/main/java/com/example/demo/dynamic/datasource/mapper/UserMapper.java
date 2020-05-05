package com.example.demo.dynamic.datasource.mapper;

import com.example.demo.dynamic.datasource.config.MyMapper;
import com.example.demo.dynamic.datasource.modle.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户 Mapper
 * </p>
 */
@Mapper
public interface UserMapper extends MyMapper<User> {
}

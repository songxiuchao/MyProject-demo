package com.example.demo.JWTSecurity.dao;

import com.example.demo.JWTSecurity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: Dao接口
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-05 17:09
 **/
@Mapper
public interface AuthDao {

    User getByAccount(@Param("account") String account);
}

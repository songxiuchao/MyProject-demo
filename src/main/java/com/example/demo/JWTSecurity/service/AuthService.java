package com.example.demo.JWTSecurity.service;

import com.example.demo.JWTSecurity.po.LoginUser;

import java.util.Map;

/**
 * 处理类
 */
public interface AuthService {
    /**
     * 检查当前登录用户是否拥有当前请求的servlet的权限
     * @return
     */
    boolean checkAll();

    /**
     * 检查当前登录用户是否拥有指定的角色访问当
     *
     * @param roleNames 角色名称集合
     */
    boolean check(String[] roleNames);

    /**
     * 登录
     * @param username 账号
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 登录（直接用账号登录）
     *
     * @param username 账号
     * @return token
     */
    String login(String username);

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    LoginUser user(String account);

    /**
     * 获取用户信息
     * @return
     */
    Map<String, Object> getUserIndexInfo();

    /**
     * 退出登录
     */
    void logout();
}

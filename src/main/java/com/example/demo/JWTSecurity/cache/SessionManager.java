package com.example.demo.JWTSecurity.cache;

import com.example.demo.JWTSecurity.po.LoginUser;

/**
 * 会话管理
 */
public interface SessionManager {
    /**
     * 缓存前缀
     */
    String SESSION_PREFIX = "LOGIN_USER_";

    /**
     * 创建会话
     *
     * @author fengshuonan
     * @Date 2019-09-28 14:50
     */
    void createSession(String token, LoginUser loginUser);

    /**
     * 获取会话
     *
     * @author fengshuonan
     * @Date 2019-09-28 14:50
     */
    LoginUser getSession(String token);

    void removeSession(String token);
}

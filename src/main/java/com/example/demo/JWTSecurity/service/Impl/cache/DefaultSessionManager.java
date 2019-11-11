package com.example.demo.JWTSecurity.service.Impl.cache;

import com.example.demo.JWTSecurity.cache.SessionManager;
import com.example.demo.JWTSecurity.po.LoginUser;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program:基于内存的会话管理
 * ps:您可以自行拓展内存管理哦
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-05 17:31
 **/
@Component
public class DefaultSessionManager implements SessionManager {

    private Map<String, LoginUser> caches = new ConcurrentHashMap<>();

    @Override
    public void createSession(String token, LoginUser loginUser) {
        caches.put(SESSION_PREFIX + token, loginUser);
    }

    @Override
    public LoginUser getSession(String token) {
        return caches.get(SESSION_PREFIX + token);
    }

    @Override
    public void removeSession(String token) {
        caches.remove(SESSION_PREFIX + token);
    }
}

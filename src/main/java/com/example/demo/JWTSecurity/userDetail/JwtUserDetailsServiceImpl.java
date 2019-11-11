package com.example.demo.JWTSecurity.userDetail;

import com.example.demo.JWTSecurity.po.LoginUser;
import com.example.demo.JWTSecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @program: 用户详情信息获取
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-07 08:03
 **/
@Service("jwtUserDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return authService.user(username);
    }
}

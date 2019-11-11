package com.example.demo.JWTSecurity.service.Impl;

import com.example.demo.JWTSecurity.Enum.AuthExceptionEnum;
import com.example.demo.JWTSecurity.context.LoginContext;
import com.example.demo.JWTSecurity.exception.AuthException;
import com.example.demo.JWTSecurity.po.LoginUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: 用户登录上下文
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-05 15:55
 **/
@Component
public class LoginContextSpringSecurityImpl implements LoginContext {
    @Override
    public LoginUser getUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
            //用户未登录
            throw new AuthException(AuthExceptionEnum.NOT_LOGIN_ERROR);
        } else {
            //获得当前已认证的用户的名字
            return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
    }

    @Override
    public boolean hasPermission(String permission) {
        return getUser().getPermissions().contains(permission);
    }

    @Override
    public boolean hasLogin() {
        //查询用户登录信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return false;
        } else {
            //判断用户是否已经登录
            if (authentication instanceof AnonymousAuthenticationToken) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    public boolean hasRole(String roleName) {
        LoginUser user = getUser();
        List<String> roleTips = getUser().getRoleTips();
        //查询是否包含该请求的用户信息
        return getUser().getRoleTips().contains(roleName);
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public boolean hasAnyRoles(String roleNames) {
        boolean hasAnyRole = false;
        if (this.hasLogin() && roleNames != null && roleNames.length() > 0) {
            for (String role : roleNames.split(",")) {
                if (hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }
        return hasAnyRole;
    }
}

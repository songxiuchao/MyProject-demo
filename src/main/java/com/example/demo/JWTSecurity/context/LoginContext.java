package com.example.demo.JWTSecurity.context;

import com.example.demo.JWTSecurity.po.LoginUser;

/**
 * 当前登录用户信息获取的接口
 */
public interface LoginContext {
    /**
     * 获取当前登录用户
     *
     * @author fengshuonan
     * @Date 2019/7/18 22:31
     */
    LoginUser getUser();

    /**
     * 验证当前用户是否拥有指定权限
     *
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    boolean hasPermission(String permission);
    /**
     * 验证当前用户是否属于以下任意一个角色
     *
     * @param roleNames 角色列表,逗号分隔
     * @return 包含:true, 否则false
     */
    boolean hasAnyRoles(String roleNames);

    /**
     * 是否登录
     *
     * @author fengshuonan
     * @Date 2019/7/18 22:31
     */
    boolean hasLogin();

    /**
     * 验证当前用户是否包含该角色
     *
     * @param roleName 角色名称
     * @return 包含:true, 否则false
     */
    boolean hasRole(String roleName);

    String getToken();

}

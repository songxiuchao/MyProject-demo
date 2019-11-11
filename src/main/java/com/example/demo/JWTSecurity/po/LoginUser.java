package com.example.demo.JWTSecurity.po;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import cn.stylefeng.roses.core.util.ToolUtil;

import java.io.Serializable;
import java.util.*;

/**
 * @program: demo
 * @description: 用户信息类
 * @author: xiuchao Song
 * @create: 2019-11-05 15:44
 **/
@Data
public class LoginUser implements UserDetails, Serializable {

    private static final long serialVersionUID = -2203426600146782417L;
    /**
     * 用户主键ID
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 角色集
     */
    private List<Long> roleList;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 角色名称集
     */
    private List<String> roleNames;

    /**
     * 角色备注（code）
     */
    private List<String> roleTips;

    private String Salt;//盐

    private String password;//密码

    private Long userId;//用户id

    private String status;//状态

    private String token;

    /**
     * 系统标识集合
     */
    private List<Map<String, Object>> systemTypes;

    /**
     * 拥有的权限
     */
    private Set<String> permissions;

    /**
     * 租户编码
     */
    private String tenantCode;

    /**
     * 租户的数据源名称
     */
    private String tenantDataSourceName;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (ToolUtil.isNotEmpty(this.roleNames)) {
            for (String roleName : this.roleNames) {
                GrantedAuthority grantedAuthority = (GrantedAuthority) () -> roleName;
                grantedAuthorities.add(grantedAuthority);
            }
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        //能生成loginUser就是jwt解析成功，没锁定
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //能生成loginUser就是jwt解析成功，没锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //能生成loginUser就是jwt解析成功，没锁定
        return true;
    }

    @Override
    public boolean isEnabled() {
        //能生成loginUser就是jwt解析成功，没锁定
        return true;
    }
}
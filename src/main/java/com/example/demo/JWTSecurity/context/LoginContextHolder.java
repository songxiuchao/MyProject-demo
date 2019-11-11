package com.example.demo.JWTSecurity.context;

import cn.stylefeng.roses.core.util.SpringContextHolder;


/**
 * @program: demo
 * @description: 当前登录用户信息获取的接口
 * @author: xiuchao Song
 * @create: 2019-11-05 15:48
 **/
public class LoginContextHolder {

    /**
     * 通过它Spring容器会自动把上下文环境对象调用ApplicationContextAware接口中的setApplicationContext方法
     * 这个方法就可以拿到静态的contants对象，从而获取到其中的变量等内容
     * @return
     */
    public static LoginContext getContext() {
        return SpringContextHolder.getBean(LoginContext.class);
    }
}

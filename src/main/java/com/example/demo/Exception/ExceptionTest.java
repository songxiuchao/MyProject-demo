package com.example.demo.Exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: demo
 * @description: 实现 HandlerExceptionResolver 接口
 * @author: xiuchao Song
 * @create: 2019-12-30 13:50
 **/
@Component
public class ExceptionTest implements HandlerExceptionResolver {

    /**
     * TODO 简单描述该方法的实现功能（可选）.
     * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        System.out.println("This is exception handler method!");
        return null;
    }
}

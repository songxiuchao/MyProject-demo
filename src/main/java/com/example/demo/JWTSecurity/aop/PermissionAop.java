package com.example.demo.JWTSecurity.aop;

import com.example.demo.JWTSecurity.annotion.Permission;
import com.example.demo.JWTSecurity.exception.PermissionException;
import com.example.demo.JWTSecurity.service.AuthService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @program: demo
 * @description: 权限检查的aop
 * @author: xiuchao Song
 * @create: 2019-11-05 15:32
 **/
@Aspect
@Order(200)
public class PermissionAop {

    @Autowired
    private AuthService authService;

    @Pointcut(value = "@annotation(com.example.demo.JWTSecurity.annotion.Permission)")
    //@Pointcut("execution(public * com.example.demo..*.*(..))")
    private void cutPermission() {

    }

    @Around("cutPermission()")
    public Object doPermission(ProceedingJoinPoint point) throws Throwable {
        //JoinPoint类，用来获取代理类和被代理类的信息
        MethodSignature ms = (MethodSignature) point.getSignature();
        //获取代理的类的方法
        Method method = ms.getMethod();
        //获取自定义注解
        Permission permission = method.getAnnotation(Permission.class);
        //拿到注解里面的参数
        String[] permissions = permission.value();
        if (permissions.length == 0) {
            //检查全体角色
            boolean result = authService.checkAll();
            if (result) {
                //环绕通知 ProceedingJoinPoint 执行proceed方法的作用是让目标方法执行，
                // 这也是环绕通知和前置、后置通知方法的一个最大区别。
                //proceed方法就是用于启动目标方法执行的
                return point.proceed();
            } else {
                throw new PermissionException();
            }

        } else {
            //检查指定角色是否有该权限访问类/接口
            boolean result = authService.check(permissions);
            if (result) {
                //执行要访问的接口
                return point.proceed();
            } else {
                throw new PermissionException();
            }
        }
    }

}

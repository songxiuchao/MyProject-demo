package com.example.demo.JWTSecurity.annotion;

import java.lang.annotation.*;

//@Inherited：
//@Inherited 元注解是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。
//如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Permission {
    /**
     * <p>角色英文名称</p>
     * <p>使用注解时加上这个值表示限制只有某个角色的才可以访问对应的资源</p>
     * <p>常用在某些资源限制只有超级管理员角色才可访问</p>
     */
    String[] value() default {};
}

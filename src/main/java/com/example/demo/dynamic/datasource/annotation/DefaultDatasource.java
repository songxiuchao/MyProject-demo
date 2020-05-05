package com.example.demo.dynamic.datasource.annotation;

import java.lang.annotation.*;

/**
 * 用户标识仅可以使用默认数据源
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultDatasource {
}

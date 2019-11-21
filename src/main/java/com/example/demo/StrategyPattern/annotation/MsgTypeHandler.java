package com.example.demo.StrategyPattern.annotation;

import com.example.demo.StrategyPattern.Enum.MSG_TYPE;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MsgTypeHandler {

    MSG_TYPE value();
}

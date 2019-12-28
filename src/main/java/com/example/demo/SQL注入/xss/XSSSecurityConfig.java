package com.example.demo.SQL注入.xss;

/**
 * @program: 安全过滤配置信息类
 * @description:
 * @author: xiuchao Song
 * @create: 2019-12-28 15:39
 **/
public class XSSSecurityConfig {

    /**
     * CHECK_HEADER：是否开启header校验
     */
    public static boolean IS_CHECK_HEADER;

    /**
     * CHECK_PARAMETER：是否开启parameter校验
     */
    public static boolean IS_CHECK_PARAMETER;

    /**
     * CHECK_URL,是否开启检查特殊url
     */
    public static boolean IS_CHECK_URL;

    /**
     * IS_LOG：是否记录日志
     */
    public static boolean IS_LOG;

    /**
     * IS_LOG：是否中断操作
     */
    public static boolean IS_CHAIN;

    /**
     * REPLACE：是否开启替换
     */
    public static boolean REPLACE;


}

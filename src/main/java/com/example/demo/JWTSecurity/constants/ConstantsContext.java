package com.example.demo.JWTSecurity.constants;

import cn.stylefeng.roses.core.util.ToolUtil;
import com.example.demo.JWTSecurity.Enum.CommonStatus;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: 系统常量的容器
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-05 16:55
 **/
@Slf4j
public class ConstantsContext {

    private static final String TIPS_END = "提示消息";

    /**
     * 所有的常量，可以增删改查
     */
    private static Map<String, Object> CONSTANTS_HOLDER = new ConcurrentHashMap<>();
    /**
     * 获取验证码开关
     */
    public static Boolean getKaptchaOpen() {
        String gunsKaptchaOpen = (String) CONSTANTS_HOLDER.get("GUNS_KAPTCHA_OPEN");
        if (CommonStatus.ENABLE.getCode().equalsIgnoreCase(gunsKaptchaOpen)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 添加系统常量
     */
    public static void putConstant(String key, Object value) {
        if (ToolUtil.isOneEmpty(key, value)) {
            return;
        }
        CONSTANTS_HOLDER.put(key, value);
    }

    /**
     * 获取系统地密钥
     */
    public static String getJwtSecret() {
        String systemReleaseVersion = (String) CONSTANTS_HOLDER.get("GUNS_JWT_SECRET");
        if (ToolUtil.isEmpty(systemReleaseVersion)) {
            String randomSecret = ToolUtil.getRandomString(32);
            CONSTANTS_HOLDER.put("GUNS_JWT_SECRET", randomSecret);
            log.error("jwt密钥存在空值！常量名称：GUNS_JWT_SECRET，采用默认值：随机字符串->" + randomSecret + TIPS_END);
            return randomSecret;
        } else {
            return systemReleaseVersion;
        }
    }

    /**
     * 获取系统地密钥过期时间（单位：秒）
     */
    public static Long getJwtSecretExpireSec() {
        Long defaultSecs = 86400L;
        String systemReleaseVersion = (String) CONSTANTS_HOLDER.get("GUNS_JWT_SECRET_EXPIRE");
        if (ToolUtil.isEmpty(systemReleaseVersion)) {
            log.error("jwt密钥存在空值！常量名称：GUNS_JWT_SECRET_EXPIRE，采用默认值：1天" + TIPS_END);
            CONSTANTS_HOLDER.put("GUNS_JWT_SECRET_EXPIRE", String.valueOf(defaultSecs));
            return defaultSecs;
        } else {
            try {
                return Long.valueOf(systemReleaseVersion);
            } catch (NumberFormatException e) {
                log.error("jwt密钥过期时间不是数字！常量名称：GUNS_JWT_SECRET_EXPIRE，采用默认值：1天" + TIPS_END);
                CONSTANTS_HOLDER.put("GUNS_JWT_SECRET_EXPIRE", String.valueOf(defaultSecs));
                return defaultSecs;
            }
        }
    }

    /**
     * 获取token的header标识
     */
    public static String getTokenHeaderName() {
        String tokenHeaderName = (String) CONSTANTS_HOLDER.get("GUNS_TOKEN_HEADER_NAME");
        if (ToolUtil.isEmpty(tokenHeaderName)) {
            String defaultName = "Authorization";
            CONSTANTS_HOLDER.put("GUNS_TOKEN_HEADER_NAME", defaultName);
            log.error("获取token的header标识为空！常量名称：GUNS_TOKEN_HEADER_NAME，采用默认值：" + defaultName + TIPS_END);
            return defaultName;
        } else {
            return tokenHeaderName;
        }
    }



}

package com.example.demo.pay.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author songxiuchao
 * @data 2019/9/17 下午 02:16
 */
public class SignUtil {

    private static final String EQUAL_STR = "=";

    public static final Logger LOGGER = LoggerFactory.getLogger(SignUtil.class);


   /* public static String getSortedString(Object obj, String concatChar, String key) {
        if (obj == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, Object> map = new ConcurrentSkipListMap<String, Object>();
        try {
            splitFieldToOrderedMap(map, obj);
            if (map.size() > 0) {
                map.entrySet().stream().forEach(p -> stringBuilder.append(p.getKey().toString()).append(EQUAL_STR).append(p.getValue()).append(concatChar));
                stringBuilder.append("key").append(EQUAL_STR).append(key);
                return stringBuilder.substring(0, stringBuilder.toString().length());
            } else {
                return null;
            }
        } catch (Throwable e) {
            LOGGER.error("exception {}", e);
            return null;
        }
    }

    private static void splitFieldToOrderedMap(Map<String, Object> map, Object obj) throws Throwable {
        Field[] fields = FieldUtil.getAllFields(obj.getClass());
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(NotSign.class) || field.isSynthetic()) {
                continue;
            }
            Object fieldObj = field.get(obj);
            String fieldName = field.getName();
            if (fieldObj == null || "".equals(fieldObj) || fieldName == "sign" || fieldName == "sign_type") {
                continue;
            }
            map.put(field.getName(), field.get(obj));
        }
    }

    public static String signature(Object object, String separator, String algorithm, String key) {
        String result = getSortedString(object, separator, key);
        if (result != null) {
            String sign = content2Sign(result, algorithm);
            LOGGER.info("sign content:{}, algorithm:{}, result:{}" , result, algorithm, sign);
            return sign;
        }
        return null;
    }*/

    public static String content2Sign(String content, String algorithm) {
        return encrypt(content, algorithm);
    }

    public static String encrypt(String s, String algorithm) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] e = s.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance(algorithm);
            mdInst.update(e);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            LOGGER.error("Sha1 error:{}", var10);
            return null;
        }
    }

}

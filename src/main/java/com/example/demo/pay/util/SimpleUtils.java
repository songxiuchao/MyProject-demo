package com.example.demo.pay.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.SortedMap;

/**
 * @author songxiuchao
 * @data 2019/9/17 下午 03:07
 */
public class SimpleUtils {

    public static JSONObject getResult(String appId, String sign, String ENCNAME, SortedMap<Object,Object> parameters){
        HashMap sort = MTResult.getSort(sign, ENCNAME,parameters);

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("appId",appId);
        jsonObject.put("random", sort.get("random"));
        jsonObject.put("sign",sort.get("result"));

        return jsonObject;
    }

    public static JSONObject getSimpleResult(String appId, String sign, String ENCNAME){
        HashMap sort = MTResult.getSort(appId,sign, ENCNAME);

        JSONObject jsonObject= new JSONObject();
        jsonObject.put("appId",appId);
        jsonObject.put("random", sort.get("random"));
        jsonObject.put("sign",sort.get("result"));

        return jsonObject;
    }

}

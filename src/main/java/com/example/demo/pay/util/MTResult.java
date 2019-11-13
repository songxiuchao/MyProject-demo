package com.example.demo.pay.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author songxiuchao
 * @data 2019/9/17 下午 01:58
 */
public class MTResult {

    public static HashMap getSort(String sign,String ENCNAME,SortedMap<Object,Object> parameters){
        String characterEncoding = "UTF-8";//指定字符集UTF-8
        //排序
        String mySign = SHA.createSign(characterEncoding,parameters);
        System.out.println("排序完: "+mySign);
        //在拼接的字符串之后添加签名的key
        String  result=mySign+"&key="+sign;
        //加密
        String s = SignUtil.content2Sign(result, ENCNAME);
        System.out.println("加密后: "+s);
        HashMap hashMap=new HashMap();
        hashMap.put("random",parameters.get("random"));
        hashMap.put("result",s);
        return hashMap;
    }
    public static HashMap getSort(String appId,String sign,String ENCNAME){
        SortedMap parameters = getMap(appId);
        String characterEncoding = "UTF-8";//指定字符集UTF-8
        //排序
        String mySign = SHA.createSign(characterEncoding,parameters);
        System.out.println("排序完: "+mySign);
        //在拼接的字符串之后添加签名的key
        String  result=mySign+"&key="+sign;
        //加密
        String s = SignUtil.content2Sign(result, ENCNAME);
        System.out.println("加密后: "+s);
        HashMap hashMap=new HashMap();
        hashMap.put("random",parameters.get("random"));
        hashMap.put("result",s);
        return hashMap;
    }

    //要排序的参数
    public static SortedMap getMap(String appId){
        SortedMap<Object,Object> parameters = new TreeMap<>();
        parameters.put("appId",appId);
        int random = MTResult.getRandom();
        parameters.put("random", random);
        return parameters;
    }


    public static Result getResult(String list, String msg){
        System.out.println("打印结果"+list);
        if (JSONObject.parseObject(list).get("status").equals("FAIL")){
            System.out.println("打印结果:=========》  Error :   "+String.valueOf(JSONObject.parseObject(list).get("errMsg")));
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR, String.valueOf(JSONObject.parseObject(list).get("errMsg")));
        }
        if (JSONObject.parseObject(list).get("status").equals("UNKNOWN")){
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR, String.valueOf(JSONObject.parseObject(list).get("errMsg")));
        }
        Object errMsg = JSONObject.parseObject(list).get("errMsg");
        if (null==errMsg){
            System.out.println("打印结果:=========》  "+"SUCCESS");
        }else {
            System.out.println("打印结果:=========》  "+ JSONObject.parseObject(list).get("errMsg"));
        }
        return Result.success(msg,list);
    }


    public static Result getPayResult(String list,String msg){
        System.out.println("打印结果"+list);
        if (JSONObject.parseObject(list).get("status").equals("FAIL")){
            System.out.println("打印结果:=========》  Error :   "+String.valueOf(JSONObject.parseObject(list).get("errMsg")));
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR, String.valueOf(JSONObject.parseObject(list).get("errMsg")));
        }
        if (JSONObject.parseObject(list).get("status").equals("UNKNOWN")){
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR, String.valueOf(JSONObject.parseObject(list).get("errMsg")));
        }
        Object errMsg = JSONObject.parseObject(list).get("errMsg");
        if (null==errMsg){
            System.out.println("打印结果:=========》  "+"SUCCESS");
        }else {
            System.out.println("打印结果:=========》  "+ JSONObject.parseObject(list).get("errMsg"));
        }
        System.out.println("==========================微信开始======================================");
        System.out.println("appId=     "+ JSONObject.parseObject(list).get("appId"));
        System.out.println("nonceStr=     "+ JSONObject.parseObject(list).get("nonceStr"));
        System.out.println("paySign=      "+ JSONObject.parseObject(list).get("paySign"));
        System.out.println("prepayId=     "+ JSONObject.parseObject(list).get("prepayId"));
        System.out.println("timeStamp=     "+ JSONObject.parseObject(list).get("timeStamp"));
        System.out.println("==========================微信开始======================================");
        System.out.println("==========================支付宝开始====================================");
        System.out.println("transactionId=     "+ JSONObject.parseObject(list).get("transactionId"));
        System.out.println("sign=     "+ JSONObject.parseObject(list).get("sign"));
        System.out.println("==========================支付宝结束=====================================");
        return Result.success(msg,list);
    }

    public static int getRandom(){
        int random=(int)(Math.random()*10+1);
        return  random;
}

}

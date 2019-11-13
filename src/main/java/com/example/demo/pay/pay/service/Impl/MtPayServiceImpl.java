package com.example.demo.pay.pay.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import com.example.demo.pay.pay.service.PayService;
import com.example.demo.pay.util.HttpClientHelper;
import com.example.demo.pay.util.MTResult;
import com.example.demo.pay.util.SimpleUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.SortedMap;

/**
 * @program: demo
 * @description: 美团支付
 * @author: xiuchao Song
 * @create: 2019-11-13 13:51
 **/
@Service("mtPayService")
@Slf4j
public class MtPayServiceImpl implements PayService {
    @Override
    public Result pay(SortedMap<Object, Object> bizMap) {
        String appId = "";
        String mchId = "";
        String precreateUrl = "";
        //支付渠道
        String channel = null;
            channel = "ali_scan_pay/wx_scan_pay";
        SortedMap map = MTResult.getMap(appId);
        map.put("outTradeNo", "");
        map.put("totalFee", "");
        map.put("subject", "");
        map.put("body", "小海葵支付");
        map.put("channel", channel);
        map.put("tradeType", "JSAPI");
        map.put("openId", "");
        map.put("expireMinutes", "30");
        map.put("merchantId", mchId); //开放平台分配的商户id, 目前是 美团POI ID
        map.put("notifyUrl", "");
        log.info("map:" + map);
        JSONObject jsonObject = SimpleUtils.getResult(appId, "sign", "SHA-256", map);
        jsonObject.put("outTradeNo", "");
        jsonObject.put("totalFee", "");
        jsonObject.put("subject", "");
        jsonObject.put("body", "小海葵支付");
        jsonObject.put("channel", channel);
        jsonObject.put("tradeType", "JSAPI");
        jsonObject.put("openId", "");
        jsonObject.put("expireMinutes", "30");
        jsonObject.put("merchantId", mchId);
        jsonObject.put("notifyUrl", "");
        log.info("美团支付请求参数-----------------------------------");
        log.info(jsonObject.toJSONString());
        String result = HttpClientHelper.sendPost(precreateUrl, jsonObject.toJSONString());
        log.info("美团支付返回结果-----------------------------------");
        log.info(result);
        JSONObject re = JSONObject.parseObject(result);
        if (re.get("status").equals("FAIL")) {
            if (re.get("subMsg") != null) {
                return Result.failure(ResponseCode.ERROR_SERVICE_VALIDATOR, "失败");
            }
        }
        JSONObject resultInfo = new JSONObject();
        re.put("bizId", appId);
        resultInfo.put("return", re.toJSONString());
        resultInfo.put("payInfo", "");
        return Result.success("支付宝手机网站调用成功！", resultInfo);
    }
}

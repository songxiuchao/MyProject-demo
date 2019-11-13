package com.example.demo.pay.pay.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import com.example.demo.pay.pay.service.PayService;
import com.example.demo.pay.util.DateUtil;
import com.example.demo.pay.util.HttpUtil;
import com.example.demo.pay.util.PayCommonUtil;
import com.example.demo.pay.util.XMLUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: demo
 * @description: 微信支付
 * @author: xiuchao Song
 * @create: 2019-11-13 13:50
 **/
@Service("wechatPayService")
public class WechatPayServiceImpl implements PayService {
    @Override
    public Result pay(SortedMap<Object, Object> bizMap) {
        String appId = "";
        String mchId = "";
        String payKey = "";
        String outOrderNo = bizMap.get("outOrderNo").toString();
        String totalAmount = new BigDecimal(bizMap.get("totalAmount").toString()).multiply(new BigDecimal(100)).intValue() + "";
        String openId = bizMap.get("openId").toString();

        String notify_url = "";//回调接口
        String trade_type = "JSAPI";// 交易类型H5支付 也可以是小程序支付参数
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        commonParams(packageParams, appId, mchId);
        packageParams.put("body","报告");// 商品描述
        packageParams.put("out_trade_no", outOrderNo);// 商户订单号
        packageParams.put("total_fee", totalAmount);// 总金额
        packageParams.put("spbill_create_ip", getLocalhostIp());// 发起人IP地址
        packageParams.put("notify_url", notify_url);// 回调地址
        packageParams.put("trade_type", trade_type);// 交易类型
        packageParams.put("openid", openId);//用户openID

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData("https://api.mch.weixin.qq.com/pay/unifiedorder", requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "");
        }
        String returnCode = (String) map.get("return_code");
        String returnMsg = (String) map.get("return_msg");
        SortedMap<Object, Object> finalpackage = new TreeMap<Object, Object>();
        if("SUCCESS".equals(returnCode)){
            String resultCode = (String) map.get("result_code");
            String errCodeDes = (String) map.get("err_code_des");
            if("SUCCESS".equals(resultCode)){
                //获取预支付交易会话标识
                String prepay_id = map.get("prepay_id").toString();
                String packages = "prepay_id=" + prepay_id;
                String timestamp = DateUtil.getTimestamp();
                String nonceStr = packageParams.get("nonce_str").toString();
                finalpackage.put("appId", appId);
                finalpackage.put("timeStamp", timestamp);
                finalpackage.put("nonceStr", nonceStr);
                finalpackage.put("package", packages);
                finalpackage.put("signType", "MD5");
                //可能报错信息(支付验证签名失败 get_brand_wcpay_request:fail)
                sign = PayCommonUtil.createSign("UTF-8", finalpackage, payKey);
                finalpackage.put("paySign", sign);
            }else{
                return Result.failure(ResponseCode.ERROR_SERVICE_VALIDATOR,"业务错误明细-微信支付返回该订单已支付");
            }
        }else{
            return Result.failure(ResponseCode.ERROR_SERVICE_VALIDATOR,"业务错误明细-微信支付返回系统错误");
        }
        JSONObject result = new JSONObject();
        result.put("return", JSON.toJSON(finalpackage));
        result.put("payInfo", "");
        return Result.success("调起支付成功！", result);
    }
    /**
     * 基础参数
     * @param packageParams
     */
    public void commonParams(SortedMap<Object, Object> packageParams, String appId, String mchId) {
        // 生成随机字符串
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        packageParams.put("appid",  appId);// 公众账号ID
        packageParams.put("mch_id",  mchId);// 商户号
        packageParams.put("nonce_str", nonce_str);// 随机字符串
    }


    // 获取当前机器的ip地址
    public static String getLocalhostIp(){
        String ip="";
        try {
            ip= InetAddress.getLocalHost().toString();
            ip=ip.substring(ip.indexOf("/")+1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ip;
    }
}

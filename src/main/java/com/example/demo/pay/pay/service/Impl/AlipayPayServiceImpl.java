/*
package com.example.demo.pay.pay.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.demo.trade.config.Constants;
import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import com.example.demo.pay.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.SortedMap;

*/
/**
 * @program: 支付宝支付
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-13 13:48
 **//*

@Service("alipayPayService")
@Slf4j
public class AlipayPayServiceImpl implements PayService {
    @Override
    public Result pay(SortedMap<Object, Object> bizMap) {
        String appId = "";
        String privateKey = "";
        String publicKey = "";
        String signType ="RSA2/RSA";

        String outOrderNo = bizMap.get("outOrderNo").toString();
        String totalAmount = bizMap.get("totalAmount").toString();
        String subject = bizMap.get("subject").toString();
        AlipayClient alipayClient = new DefaultAlipayClient("RSA2", appId,
                privateKey, "json","utf-8",
                publicKey, signType);
        //手机网站支付，支付请求在服务端实现
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
        String returnUrl =  "";
        alipayRequest.setReturnUrl(returnUrl);//前台通知
        alipayRequest.setNotifyUrl("");//后台回调
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", outOrderNo);
        bizContent.put("total_amount", totalAmount);//订单金额:元
        bizContent.put("subject",subject);//订单标题
        bizContent.put("product_code", "QUICK_WAP_PAY");//手机网页支付
        bizContent.put("timeout_express", "5m");//该笔订单允许的最晚付款时间
        bizContent.put("body", "小海葵支付");
        String biz = bizContent.toString().replaceAll("\"", "'");
        alipayRequest.setBizContent(biz);
        log.info("业务参数:"+alipayRequest.getBizContent());
        String form = Constants.FAILED;
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("支付宝构造表单失败",e);
            return Result.failure(ResponseCode.ERROR_999,"业务错误明细-支付宝构造表单失败");
        }
        JSONObject result = new JSONObject();
        result.put("return", form);
        result.put("payInfo", "");
        return Result.success("支付宝手机网站调用成功！", result);
    }
}
*/

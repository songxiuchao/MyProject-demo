package com.example.demo.pay.back;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.example.demo.pay.util.MobileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: demo
 * @description: 支付回调
 * @author: xiuchao Song
 * @create: 2019-11-13 08:28
 **/
@RestController
@RequestMapping("/payBack")
@Slf4j
public class PayBack {
    /**
     * 支付宝回调
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/aliPayNotify")
    public void aliPayNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String  message = "success";
        Map<String, String> params = new HashMap<String, String>();
        // 取出所有参数是为了验证签名
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }
        String outTradeNo = params.get("out_trade_no");
        String tradeNo = params.get("trade_no");
        log.info("支付宝回调返回参数" + params.toString());
        //获取订单详情
        //......略掉....
        String publicKey=""; //去支付宝查看
        String signType="";//去支付宝查看
        //验证签名 校验签名
        boolean signVerified = false;
        try {
            //此处的param是加上订单详情
            signVerified = AlipaySignature.rsaCheckV1(params, publicKey, "utf-8", signType);
            //各位同学这里可能需要注意一下,2018/01/26 以后新建应用只支持RSA2签名方式，目前已使用RSA签名方式的应用仍然可以正常调用接口，注意下自己生成密钥的签名算法
            //signVerified = AlipaySignature.rsaCheckV1(params, Configs.getAlipayPublicKey(), "UTF-8","RSA2");
            //有些同学通过 可能使用了这个API导致验签失败，特此说明一下
            //signVerified = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(), "UTF-8");//正式环境
        } catch (AlipayApiException e) {
            e.printStackTrace();
            message =  "failed";
        }

        if (signVerified) {
            log.info("支付宝验证签名成功！");
            String appId="";

        //比较从订单中取出的appid和从param中取出的appid
            if (!appId.equals(params.get("app_id"))) {
                log.info("与付款时的appid不同，此为异常通知，应忽略！");
                message =  "failed";
            }else {
                //在数据库中查找订单号对应的订单，并将其金额与数据库中的金额对比，若对不上，也为异常通知
                String status = params.get("trade_status");
                if (status.equals("WAIT_BUYER_PAY")) { // 如果状态是正在等待用户付款
                    log.info(outTradeNo + "订单的状态正在等待用户付款");
                } else if (status.equals("TRADE_CLOSED")) { // 如果状态是未付款交易超时关闭，或支付完成后全额退款
                    log.info(outTradeNo + "订单的状态已经关闭");
                } else if (status.equals("TRADE_SUCCESS") || status.equals("TRADE_FINISHED")) { // 如果状态是已经支付成功
                    //执行业务，比如向MQ写入消息
                }
            }
        }else {
            // 如果验证签名没有通过
            message =  "failed";
            log.info("验证签名失败！");
        }
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(message.getBytes());
        out.flush();
        out.close();
    }



    /**
     * 微信支付后回调方法
     * @param request
     * @param response
     */
    @RequestMapping(value = "/weChatNotify")
    public void weChatNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("进入微信回调------------------------------------");
        String resXml = "";
        try {
            //解析XML
            Map<String, String> map = MobileUtil.parseXml(request);
            String returnCode = map.get("return_code");//状态
            String outTradeNo = map.get("out_trade_no");//订单号
            String tradeNo = map.get("transaction_id");//第三方支付订单号
            log.info("微信回调返回参数" + map.toString());

            //保存订单日志

            //构建订单信息
            if (returnCode.equals("SUCCESS")) {
                if (outTradeNo != null) {
                    //处理订单逻辑
                    log.info("微信手机支付回调成功订单号:{}",outTradeNo);
                    // 向MQ写入消息 --> "fanout.payment" <-- payment.info
                    //....
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }
            }else{
                log.info("微信手机支付回调失败订单号:{}",outTradeNo);
                // 向MQ写入消息 --> "fanout.payment" <-- payment.info
                //.....
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }

        }catch (Exception e) {
            log.error("手机支付回调通知失败",e);
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        try {
            // ------------------------------
            // 处理业务完毕
            // ------------------------------
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 美团支付后回调方法
     * @param request
     * @param response
     */
    @RequestMapping(value = "/meituanNotify")
    public void meituanNotify(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("进入美团支付回调------------------------------------");
        Map<String, String> params = new HashMap<String, String>();
        // 取出所有参数是为了验证签名
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            params.put(parameterName, request.getParameter(parameterName));
        }

        //构建订单信息

        //向MQ写入消息

        //.....

        JSONObject json  = new JSONObject();
        json.put("status", "SUCCESS");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        out.write(json.toJSONString().getBytes());
        out.flush();
        out.close();

    }
}

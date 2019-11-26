package com.example.demo.Settle.weChat;

import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import com.example.demo.Settle.constant.Wechat.WechatConstant;
import com.example.demo.Settle.util.HttpUtil;
import com.example.demo.Settle.util.PayCommonUtil;
import com.example.demo.Settle.util.XMLUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @program: 微信分账
 * @description:
 * @author: xiuchao Song
 * @create: 2019-11-26 09:53
 **/
@RestController
@RequestMapping("/weChatSettle")
public class WeChatTest {

    @Value("${mchId}")
    private String mchId;
    @Value("${subMchId}")
    private String subMchId;
    @Value("${appid}")
    private String appid;
//    @Value("${subAppid}")
//    private String subAppid;
    @Value("${signType}")
    private String signType;
    @Value("${payKey}")
    private String payKey;

    //@RequestParam String mchId,@RequestParam String subMchId,@RequestParam String appid,
    //                                         @RequestParam(required = false) String subAppid,@RequestParam String nonceStr,
    //                                         @RequestParam String sign,@RequestParam String signType,

    /**
     * 绑定关系
     * @param type
     * @param account
     * @param name
     * @param relationType
     * @param customRelation
     * @return
     */
    @PostMapping("/bind")
    public Result bind(@RequestParam String type,
                                         @RequestParam String account,@RequestParam String name,@RequestParam String relationType,
                                         @RequestParam(required = false) String customRelation){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        SortedMap<Object, Object> userInfoParam = new TreeMap<>();
        userInfoParam.put("type",type);
        userInfoParam.put("account",account);
        userInfoParam.put("name",name);
        userInfoParam.put("relation_type",relationType);
        userInfoParam.put("custom_relation",customRelation);
        packageParams.put("receiver",userInfoParam);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.bind, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }

    /**
     * 解绑
     * @return
     */
    @PostMapping("/removeBind")
    public Result removeBind(@RequestParam String type, @RequestParam String account){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        SortedMap<Object, Object> userInfoParam = new TreeMap<>();
        userInfoParam.put("type",type);
        userInfoParam.put("account",account);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.removeBind, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }



    /**
     * 单笔分账
     * @param transactionId
     * @param outOrderNo
     * @param type
     * @param account
     * @param amount
     * @param description
     * @return
     */
    @PostMapping("/settleOne")
    public Result settleOne(@RequestParam String transactionId, @RequestParam String outOrderNo,@RequestParam String type,
                            @RequestParam String account,@RequestParam String amount,@RequestParam String description){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        packageParams.put("transaction_id",transactionId);
        packageParams.put("out_order_no",outOrderNo);

        SortedMap<Object, Object> userInfoParam = new TreeMap<>();
        userInfoParam.put("type",type);
        userInfoParam.put("account",account);
        userInfoParam.put("amount",amount);
        userInfoParam.put("description",description);
        packageParams.put("receiver",userInfoParam);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.settleOne, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }

    /**
     * 多笔分账
     */
    @PostMapping("/settleMore")
    public Result settleMore(@RequestParam String transactionId, @RequestParam String outOrderNo,@RequestParam String type,
                             @RequestParam String account,@RequestParam String amount,@RequestParam String description){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        packageParams.put("transaction_id",transactionId);
        packageParams.put("out_order_no",outOrderNo);

        SortedMap<Object, Object> userInfoParam = new TreeMap<>();
        userInfoParam.put("type",type);
        userInfoParam.put("account",account);
        userInfoParam.put("amount",amount);
        userInfoParam.put("description",description);
        packageParams.put("receiver",userInfoParam);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.settleOne, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }

    /**
     * 分账回退（退款）
     * @return
     */
    @PostMapping("/refund")
    public Result refund(@RequestParam(required = false) String orderId,@RequestParam(required = false)String outOrderNo,
                         @RequestParam String outReturnNo,@RequestParam String returnAccountType,
                         @RequestParam String returnAccount,@RequestParam String returnAmount,@RequestParam String description){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        packageParams.put("order_id",orderId);
        packageParams.put("out_order_no",outOrderNo);
        packageParams.put("out_return_no",outReturnNo);
        packageParams.put("return_account_type",returnAccountType);
        packageParams.put("return_account",returnAccount);
        packageParams.put("return_amount",returnAmount);
        packageParams.put("description",description);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.refund, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }

    /**
     * 完结分账
     * @param outOrderNo
     * @param description
     * @return
     */
    @PostMapping("/finishSettle")
    public Result finishSettle(@RequestParam(required = false)String outOrderNo,@RequestParam String description){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        packageParams.put("out_order_no",outOrderNo);
        packageParams.put("description",description);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.finishSettle, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }

    /**
     * 查询分账结果
     * @return
     */
    @PostMapping("/querySettle")
    public Result querySettle(){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.querySettle, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }

    /**
     * 查询退款结果
     * @param orderId
     * @param outOrderNo
     * @param outReturnNo
     * @return
     */
    @PostMapping("/resultQuery")
    public Result resultQuery(@RequestParam String orderId,@RequestParam String outOrderNo,@RequestParam String outReturnNo){
        SortedMap<Object, Object> packageParams = new TreeMap<>();
        getCommon(packageParams);
        packageParams.put("order_id",orderId);
        packageParams.put("out_order_no",outOrderNo);
        packageParams.put("out_return_no",outReturnNo);

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, payKey);
        packageParams.put("sign", sign);// 签名

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        String resXml = HttpUtil.postData(WechatConstant.queryRefund, requestXML);
        Map map = null;
        try {
            map = XMLUtil.doXMLParse(resXml);
        }catch (Exception e) {
            return Result.failure(ResponseCode.ERROR_999, "业务错误明细-微信支付返回XML处理失败");
        }
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            return Result.success("SUCCESS");
        }else{
            return Result.failure(ResponseCode.ERROR_DATA_VALIDATOR,(String)map.get("return_msg"));
        }
    }


    /**
     * 公共参数
     * @param packageParams
     * @return
     */
    public SortedMap getCommon(SortedMap<Object, Object> packageParams){
        // 生成随机字符串
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonce_str = strTime + strRandom;
        packageParams.put("mch_id",mchId);
        packageParams.put("sub_mch_id",subMchId);
        packageParams.put("appid",appid);
        //packageParams.put("sub_appid",subAppid);
        packageParams.put("nonce_str",nonce_str);
        packageParams.put("sign_type",signType);
        return packageParams;
    }
}

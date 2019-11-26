package com.example.demo.Settle.constant.Wechat;

/**
 * @program: demo
 * @description: 微信常量配置
 * @author: xiuchao Song
 * @create: 2019-11-26 11:29
 **/
public class WechatConstant {
    /**
     * 微信绑定的接口
     */
    public static final String bind="https://api.mch.weixin.qq.com/pay/profitsharingaddreceiver";

    /**
     * 微信单次分账
     */
    public static final String settleOne="https://api.mch.weixin.qq.com/secapi/pay/profitsharing";

    /**
     * 解绑
     */
    public static  final String removeBind="https://api.mch.weixin.qq.com/pay/profitsharingremovereceiver";

    /**
     * 退款(账单回退)
     */
    public static  final String refund="https://api.mch.weixin.qq.com/secapi/pay/profitsharingreturn";

    /**
     * 完结分账
     */
    public static final String finishSettle="https://api.mch.weixin.qq.com/secapi/pay/profitsharingfinish";

    /**
     * 查询分账结果
     */
    public static final String querySettle="https://api.mch.weixin.qq.com/pay/profitsharingquery";

    /**
     * 查询退款结果
     */
    public static final String queryRefund="https://api.mch.weixin.qq.com/pay/profitsharingreturnquery";

    /**
     * 多笔分账
     */
    public static final String settleMore="https://api.mch.weixin.qq.com/secapi/pay/multiprofitsharing";
}

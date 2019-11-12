package com.example.demo.wechat;

/**
 * 配置类
 *
 * @author snps 2019年7月24日下午5:11:07
 */
public class WechatConfig {

	/**
	 * 获取code的请求地址（是获取openid之前的必要步骤）
	 */
	static final String URL_GET_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize";


	/**
	 * 获取ACCESS_TOKEN的请求地址
	 */
	static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * 获取用户信息的请求地址
	 */
	static final String URL_GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token";





	/**
	 * 开发者ID(AppID)
	 */
	public static final String APPID = "wx678e17db5c23a396";

	/**
	 * 开发者密码(AppSecret)
	 */
	public static final String APPSECRET = "bdf021d28aac723e8302e95f00aeafc0";

	/**
	 * 微信公众平台-开发-基本配置-Token
	 */
	public static final String TOKEN = "Abc123456";


	/**
	 * 获取凭证（GET）
	 */
	public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	/**
	 * 发送模板消息
	 */
	public static final String TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	/**
	 * 消息模板Id
	 */
	public static final String TEMPLATE_ID = "TpWLp1HtoMMV8C5-K9njyybdw6tTnTL_4dwuNlZl8_k";

}
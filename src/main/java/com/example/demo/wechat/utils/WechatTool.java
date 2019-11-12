package com.example.demo.wechat.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.wechat.WechatConfig;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 工具类
 * 
 * @author snps 2019年7月24日下午5:13:17
 */
@Slf4j
public class WechatTool {

	/**
	 * 存放Token的集合
	 */
	private static Map<String, AccessToken> mapToken;

	static {
		mapToken = new HashMap<String, AccessToken>();
	}

	public static Map<String, AccessToken> getMapToken() {
		return mapToken;
	}

	public static void setMapToken(Map<String, AccessToken> mapToken) {
		WechatTool.mapToken = mapToken;
	}

	/**
	 * 验证签名
	 * @param signature 签名
	 * @param token TOKEN（开发者在微信 后台设置的）
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return Boolean
	 */
	public static boolean checkSignature(String signature, String token, String timestamp, String nonce) {
		// 将token、timestamp、nonce三个参数进行字典序排序
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);

		// 将三个参数字符串拼接成一个字符串进行sha1加密
		String shaStr = getSha1(arr[0] + arr[1] + arr[2]);

		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return shaStr != null ? shaStr.equals(signature) : false;
	}

	/**
	 * 获得Token
	 * @param appId
	 * @param appSecret
	 * @return AccessToken
	 */
	public static AccessToken getToken(String appId, String appSecret) {
		AccessToken token = null;

		// 从Redis中获取Token...（外网测试环境未安装Redis，后面替换实现）
		token = WechatTool.getMapToken().get(appId);
		if (token != null) {
			return token;
		} else {
			token = createToken(appId, appSecret);
			return token;
		}
	}

	/**
	 * 生成Token
	 * @param appid
	 * @param appsecret
	 * @return AccessToken
	 */
	private static AccessToken createToken(String appid, String appsecret) {
		AccessToken token = null;
		String requestUrl = WechatConfig.TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		String response = WechatTool.httpsRequest(requestUrl, "GET", null);
		JSONObject jsonObject = JSON.parseObject(response);
		
		if (null != jsonObject) {
			try {
				token = new AccessToken();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInteger("expires_in"));

				// 缓存Token
				getMapToken().put(appid, token);
				token = getMapToken().get(appid);
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInteger("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return token;
	}

	/**
	 * 发送请求以https方式发送请求并将请求响应内容以String方式返回
	 * @param url 请求地址
	 * @param method 请求方法(get / post)
	 * @param body 请求数据
	 * @return 请求响应内容转换成字符串信息
	 */
	public static String httpsRequest(String url, String method, String body) {
		if (url == null || method == null) {
			return null;
		}

		String response = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpsURLConnection conn = null;

		try {
			TrustManager[] tm = {new JEEWeiXinX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL requestUrl = new URL(url);
			conn = (HttpsURLConnection) requestUrl.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(method);

			if (null != body) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(body.getBytes("UTF-8"));
				outputStream.close();
			}

			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			response = buffer.toString();

		} catch (Exception e) {
			log.error("发送请求出错！");
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			} catch (IOException execption) {
				log.error("释放资源出错！");
			}
		}
		return response;
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return String
	 */
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("UTF-8"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}

}
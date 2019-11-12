package com.example.demo.wechat.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.wechat.WechatConfig;
import com.example.demo.wechat.message.vo.MessageType;
import com.example.demo.wechat.message.vo.TemplateData;
import com.example.demo.wechat.message.vo.WechatTemplate;
import com.example.demo.wechat.utils.AccessToken;
import com.example.demo.wechat.utils.WechatTool;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理工具类
 * 
 * @author snps 2019年7月25日下午4:36:08
 */
@Slf4j
public class MessageHandlerUtil {

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 *            封装了请求信息的HttpServletRequest对象
	 * @return Map<String, String> 解析结果
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将请求解析结果存储在HashMap中
		Map<String, String> mapData = new HashMap<String, String>();

		// 从request中获取输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> lstElement = root.elements();
		// 遍历所有子节点，构造Map<String, String>类型的数据
		for (Element e : lstElement) {
			mapData.put(e.getName(), e.getText());
		}

		// 释放资源
		inputStream.close();
		inputStream = null;
		return mapData;
	}

	/**
	 * 根据消息类型构造返回消息
	 * 
	 * @param mapRequestData
	 * @return String responseMessage(响应消息)
	 */
	public static String buildResponseMessage(Map<String, String> mapRequestData) {
		// 响应消息
		String responseMessage = "";
		// 得到消息类型
		String msgType = mapRequestData.get("MsgType").toString();
		log.info("消息类型：" + msgType);

		// 根据“消息类型”，做不同消息处理
		MessageType messageEnumType = MessageType.valueOf(MessageType.class, msgType.toUpperCase());

		switch (messageEnumType) {
		case TEXT:
			// 处理文本消息
			responseMessage = handleTextMessage(mapRequestData);
			break;
		case IMAGE:
			// 处理图片消息
			break;
		case VOICE:
			// 处理语音消息
			break;
		case VIDEO:
			// 处理视频消息
			break;
		case SHORTVIDEO:
			// 处理小视频消息
			break;
		case LOCATION:
			// 处理位置消息
			break;
		case LINK:
			// 处理链接消息
			responseMessage = handleEventMessage(mapRequestData);
			break;
		case EVENT:
			// 处理事件消息,用户在关注与取消关注公众号时，微信会向我们的公众号服务器发送事件消息,开发者接收到事件消息后就可以给用户下发欢迎消息
			break;
		default:
			break;
		}
		// 返回响应消息
		return responseMessage;
	}

	/**
	 * 接收到文本消息后处理
	 * 
	 * @param mapRequestData
	 * @return String
	 */
	private static String handleTextMessage(Map<String, String> mapRequestData) {
		// 响应消息
		String responseMessage = "";
		// 消息内容类型
		String content = mapRequestData.get("Content");
		switch (content) {
		case "文本":
			String msgText = "欢迎光临！";
			responseMessage = buildTextMessage(mapRequestData, msgText);
			break;
		case "模板":
			String templateId = WechatConfig.TEMPLATE_ID;
			responseMessage = buildTempMessage(mapRequestData, templateId);
			break;
		case "图片":
			break;
		case "图文":
			break;
		case "音乐":
			break;
		default:
			// responseMessage = buildWelcomeTextMessage(mapRequestData);
			break;
		}
		
		// 返回响应消息
		return responseMessage;
	}

	/**
	 * 构造文本消息
	 * 
	 * @param mapRequestData
	 * @param content
	 *            文本消息内容
	 * @return 文本消息XML字符串
	 */
	private static String buildTextMessage(Map<String, String> mapRequestData, String content) {
		// 发送方帐号
		String fromUserName = mapRequestData.get("FromUserName");
		// 开发者微信号
		String toUserName = mapRequestData.get("ToUserName");

		return String.format(
				"<xml>" + "<ToUserName><![CDATA[%s]]></ToUserName>" + "<FromUserName><![CDATA[%s]]></FromUserName>"
						+ "<CreateTime>%s</CreateTime>" + "<MsgType><![CDATA[text]]></MsgType>"
						+ "<Content><![CDATA[%s]]></Content>" + "</xml>",
				fromUserName, toUserName, getMessageCreateTime(), content);
	}

	/**
	 * 构建欢迎提示消息
	 * 
	 * @param mapRequestData
	 * @return responseMessageXml
	 */
	private static String buildWelcomeTextMessage(Map<String, String> mapRequestData) {
		String responseXmlMessage;

		String fromUserName = mapRequestData.get("FromUserName");
		String toUserName = mapRequestData.get("ToUserName");

		responseXmlMessage = String.format(
				"<xml>" + "<ToUserName><![CDATA[%s]]></ToUserName>" + "<FromUserName><![CDATA[%s]]></FromUserName>"
						+ "<CreateTime>%s</CreateTime>" + "<MsgType><![CDATA[text]]></MsgType>"
						+ "<Content><![CDATA[%s]]></Content>" + "</xml>",
				fromUserName, toUserName, getMessageCreateTime(), "感谢您的关注!");
		return responseXmlMessage;
	}

	/**
	 * 发送模板消息调用实例
	 * @param mapRequestData
	 * @param templateId
	 * @return String
	 */
	private static String buildTempMessage(Map<String, String> mapRequestData, String templateId) {
		log.info("构造模板消息...");

		String fromUserName = mapRequestData.get("FromUserName");

		WechatTemplate template = new WechatTemplate();
		template.setUrl("www.baidu.com");
		template.setTouser(fromUserName);
		template.setTopcolor("#000000");
		template.setTemplate_id(templateId);

		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		TemplateData first = new TemplateData();
		first.setColor("#000000");
		first.setValue("您好，您有一条待确认订单。");
		m.put("first", first);
		TemplateData keyword1 = new TemplateData();
		keyword1.setColor("#328392");
		keyword1.setValue("O00001");
		m.put("keyword1", keyword1);
		TemplateData keyword2 = new TemplateData();
		keyword2.setColor("#328392");
		keyword2.setValue("预定订单");
		m.put("keyword2", keyword2);
		TemplateData keyword3 = new TemplateData();
		keyword3.setColor("#328392");
		keyword3.setValue("可口可乐");
		m.put("keyword3", keyword3);
		TemplateData keyword4 = new TemplateData();
		keyword4.setColor("#328392");
		keyword4.setValue("可口可乐");
		m.put("keyword4", keyword4);
		TemplateData remark = new TemplateData();
		remark.setColor("#929232");
		remark.setValue("请及时确认订单！");
		m.put("remark", remark);
		template.setData(m);

//		AccessToken token = WechatTool.getToken(WechatConfig.APPID, WechatConfig.APPSECRET);
		AccessToken token = WechatTool.getToken("wx678e17db5c23a396","bdf021d28aac723e8302e95f00aeafc0");
		sendMessage(template, token.getAccessToken());
		return "";
	}

	private static int sendMessage(WechatTemplate template, String accessToken) {
		log.info("发送模板消息...");

		int result = 0;
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN"
				.replace("ACCESS_TOKEN", accessToken);
		String json = JSONObject.toJSONString(template);
		String response = WechatTool.httpsRequest(url, "POST", json);
		JSONObject jsonObject = JSON.parseObject(response);
		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = jsonObject.getIntValue("errcode");
			}
		}
		return result;
	}

	/**
	 * 处理事件消息Message
	 * 
	 * @param mapRequestData
	 * @return
	 */
	private static String handleEventMessage(Map<String, String> mapRequestData) {
		String content = mapRequestData.get("Event");
		log.info("事件类型：" + content);

		String responseMessage;
		switch (content) {
		case "SCAN":// 扫码进入
			responseMessage = "";
			break;
		case "SUBSCRIBE":// 关注
			responseMessage = buildWelcomeTextMessage(mapRequestData);
			break;
		case "TEMPLATESENDJOBFINISH":// 模板发送完成
			responseMessage = "";
			break;
		default:
			responseMessage = buildWelcomeTextMessage(mapRequestData);
			break;

		}
		return responseMessage;
	}

	/**
	 * 生成消息创建时间 （整型）
	 * 
	 * @return 消息创建时间
	 */
	private static String getMessageCreateTime() {
		// 如果不需要格式,可直接用dt,dt就是当前系统时间
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmm");// 设置显示格式
		String nowTime = df.format(dt);
		long dd = (long) 0;
		try {
			dd = df.parse(nowTime).getTime();
		} catch (Exception e) {

		}
		return String.valueOf(dd);
	}

}
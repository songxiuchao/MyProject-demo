package com.example.demo.wechat.message.vo;

import java.util.Map;

/**
 * 模板消息封装类
 * 
 * @author snps 2019年8月19日上午9:10:46
 */
public class WechatTemplate {

	/**
	 * 接收者(openid)
	 */
	private String touser;

	/**
	 * 模板ID
	 */
	private String template_id;

	/**
	 * 模板跳转链接
	 */
	private String url;

	/**
	 * 字体颜色
	 */
	private String topcolor;

	/**
	 * 模板里的数据
	 */
	private Map<String, TemplateData> data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Map<String, TemplateData> getData() {
		return data;
	}

	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}

	
	@Override
	public String toString() {
		return "WechatTemplate [touser=" + touser + ", template_id=" + template_id + ", url=" + url + ", topcolor="
				+ topcolor + ", data=" + data + "]";
	}

	
}
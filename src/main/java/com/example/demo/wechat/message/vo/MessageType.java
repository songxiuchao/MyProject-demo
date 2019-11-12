package com.example.demo.wechat.message.vo;

/**
 * 消息类型
 * 
 * @author snps 2019年7月24日下午2:06:43
 */
public enum MessageType {
	
	/**
	 * 文本消息
	 */
	TEXT,
	/**
	 * 图片消息
	 */
	IMAGE,
	/**
	 * 语音消息
	 */
	VOICE, 
	/**
	 * 视频消息
	 */
	VIDEO, 
	SHORTVIDEO,
	/**
	 * 小视频消息
	 */
	LOCATION,
	/**
	 * 链接消息
	 */
	LINK,
	/**
	 * 事件消息
	 */
	EVENT
	
}
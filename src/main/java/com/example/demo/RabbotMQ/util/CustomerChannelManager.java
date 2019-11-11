package com.example.demo.RabbotMQ.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import io.netty.channel.Channel;

/**
 * 客户端通道管理类
 * 
 * @author snps 2019年7月8日下午4:58:33
 */
public class CustomerChannelManager {
	
	/**
	 * 声明一个用于存放客户端连接的集合
	 */
	private static Map<String, Channel> customerChannelMap;
	
	static {
		customerChannelMap = new ConcurrentHashMap<String, Channel>(200);
	}

	/**
	 * 声明一个内部类，通过内部类来初始化本类，保证本类只会有一个实例
	 */
	private static class CustomerChannelManagerHolder {
		private static final CustomerChannelManager INSTANCE = new CustomerChannelManager();
	}
	
	/**
	 * 私有化本类构造方法，避免通过外部创建类实例
	 */
	private CustomerChannelManager() {
	}
	
	/**
	 * 提供类实例初始化方法
	 */
	public static final CustomerChannelManager getInstance() {
		return CustomerChannelManagerHolder.INSTANCE;
	}
	
	
	/********************************************************************
	 * 保存客户端连接
	 * @param channelId 连接标识
	 * @param channel 客户端连接
	 */
	public static final void putChannel(String channelId, Channel channel) {
		customerChannelMap.put(channelId, channel);
	}
	
	
	/**
	 * 通过连接标识获取客户端连接通道
	 * @param channelId 连接标识
	 * @return io.netty.channel.Channel
	 */
	public static final Channel getChannel(String channelId) {
		return customerChannelMap.get(channelId);
	}
	
	/**
	 * 释放客户端连接
	 * @param channel
	 */
	public static final void releaseChannel(Channel channel) {
		customerChannelMap.remove(channel);
	}
	
	/**
	 * 通过连接标识删除客户端连接
	 * @param channel
	 */
	public static final void deleteChannel(String channelId) {
		if(customerChannelMap.containsKey(channelId)) {
			Channel channel = customerChannelMap.get(channelId);
			customerChannelMap.remove(channel);
		}
	}
	
	/**
	 * 获取已连接的客户端数量
	 * @return int
	 */
	public static final int getChannelSize() {
		return customerChannelMap.size();
	}
	
}
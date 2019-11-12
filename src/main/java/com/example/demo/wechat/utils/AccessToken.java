package com.example.demo.wechat.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * 凭证
 * @author snps 2019年7月24日下午5:11:39
 */
@Setter
@Getter
public class AccessToken {

	/**
	 * 获取到的凭证
	 */
	private String accessToken;

	/**
	 * 凭证有效时间，单位：秒
	 */
	private int expiresIn;

}
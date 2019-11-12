package com.example.demo.wechat.Login.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 网页授权信息
 * @author songxiuchao
 * @data 2019/8/17 15:02
 */
@Data
@ToString
public class Oauth2Token implements Serializable {
	
	private static final long serialVersionUID = 5121982729579854468L;
	
	/**
	 * 网页授权接口调用凭证
	 */
    private String accessToken;

    /**
     * 凭证有效时长
     */
    private int expiresIn;
    
    /**
     * 用于刷新凭证
     */
    private String refreshToken;
    
    /**
     * 用户标识
     */
    private String openId;
    
    /**
     * 用户授权作用域
     */
    private String scope;

}
package com.example.demo.wechat.Login.vo;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 通过网页授权获取的用户信息
 * @author songxiuchao
 * @data 2019/8/17 15:04
 */
@Data
@ToString
public class SNSUserInfo implements Serializable {
	
	private static final long serialVersionUID = 8502475644713239642L;
	
	/**
	 * 用户标识
	 */
    private String openId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 性别（1是男性，2是女性，0是未知）
     */
    private int sex;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 用户头像链接
     */
    private String headImgUrl;

    /**
     * 用户特权信息
     */
    private List<String> privilegeList;

    /**
     * UnionId
     */
    private String unionid;
   
}
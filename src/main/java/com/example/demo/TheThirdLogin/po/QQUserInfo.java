package com.example.demo.TheThirdLogin.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class QQUserInfo implements Serializable {

	private static final long serialVersionUID = -8487807148181131126L;

	private Integer ret;
	private String msg;
	private Integer is_lost;
	private String nickname;
	private String gender;
	private String province;
	private String city;
	private String year;
	private String constellation;
	private String figureurl;
	private String figureurl_1;
	private String figureurl_2;
	private String figureurl_qq;
	private String figureurl_qq_1;
	private String figureurl_qq_2;
	private String is_yellow_vip;
	private String vip;
	private String yellow_vip_level;
	private String level;
	private String is_yellow_year_vip;
}

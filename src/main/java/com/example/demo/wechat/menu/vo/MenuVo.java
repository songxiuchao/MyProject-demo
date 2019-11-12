package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author songxiuchao
 * @data 2019/8/16 17:28
 */
@Data
public class MenuVo extends Button implements Serializable {

	private static final long serialVersionUID = -1424483357188290681L;

	private String type;

	private String key;

	private String url;

	private String name;

	private List<Subbutton> sub_button;
	
}
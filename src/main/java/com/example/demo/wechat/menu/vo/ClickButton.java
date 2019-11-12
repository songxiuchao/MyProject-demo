package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author songxiuchao
 * @data 2019/8/16 11:04
 */

@Data
public class ClickButton extends Button implements Serializable {
    
	private static final long serialVersionUID = 3464237757157281496L;

    private String key;
    
}
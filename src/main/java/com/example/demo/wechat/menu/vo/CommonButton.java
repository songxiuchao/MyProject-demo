package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author songxiuchao
 * @data 2019/8/16 11:32
 */
@Data
public class CommonButton extends Button implements Serializable {

    private static final long serialVersionUID = -7200712857808337631L;

    private String type;

    private String key;

    private String url;

}
package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author songxiuchao
 * @data 2019/8/17 9:32
 */
@Data
public class Subbutton extends Button implements Serializable {

    private static final long serialVersionUID = 4888825923908215277L;

    private String type;

    private String key;

    private String url;

}
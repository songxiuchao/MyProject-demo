package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author songxiuchao
 * @data 2019/8/16 11:32
 */
@Data
public class ComplexButton extends Button implements Serializable {

    private static final long serialVersionUID = -4149222352119554254L;

    private String type;

    private String key;

    private String url;

    private List<CommonButton> sub_button;
    
}
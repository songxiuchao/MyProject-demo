package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author songxiuchao
 * @data 2019/8/16 11:06
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = 4267051262822653214L;

    private List<ComplexButton> button;
    
}
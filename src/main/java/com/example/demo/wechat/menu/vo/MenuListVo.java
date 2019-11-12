package com.example.demo.wechat.menu.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author songxiuchao
 * @data 2019/8/17 9:29
 */
@Data
public class MenuListVo  implements Serializable {

    private static final long serialVersionUID = -4952481219793071753L;

    private List<MenuVo> button;

}
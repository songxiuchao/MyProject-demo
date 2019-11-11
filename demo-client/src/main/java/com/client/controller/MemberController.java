package com.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: demo
 * @description: 测试类
 * @author: xiuchao Song
 * @create: 2019-11-09 11:22
 **/
@RestController
@RequestMapping("/member")
public class MemberController {

    /**
     * 会员列表页面
     */
    @RequestMapping("/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("member/list");
        return modelAndView;
    }

}

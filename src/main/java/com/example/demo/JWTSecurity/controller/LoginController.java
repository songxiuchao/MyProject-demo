package com.example.demo.JWTSecurity.controller;

import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.reqres.response.SuccessResponseData;
import cn.stylefeng.roses.core.util.HttpContext;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.example.demo.JWTSecurity.cache.SessionManager;
import com.example.demo.JWTSecurity.constants.ConstantsContext;
import com.example.demo.JWTSecurity.context.LoginContextHolder;
import com.example.demo.JWTSecurity.exception.InvalidKaptchaException;
import com.example.demo.JWTSecurity.po.LoginUser;
import com.example.demo.JWTSecurity.service.AuthService;
import com.google.code.kaptcha.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Objects;

import static io.netty.handler.codec.rtsp.RtspMethods.REDIRECT;

/**
 * @program: demo
 * @description: 登录测试类
 * @author: xiuchao Song
 * @create: 2019-11-05 16:40
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private AuthService authService;
    @Resource
    private SessionManager sessionManager;


    /**
     * 跳转到主页
     * @author fengshuonan
     * @Date 2018/12/23 5:41 PM
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(Model model) {
    /*    //判断用户是否登录
        if (LoginContextHolder.getContext().hasLogin()) {
            Map<String, Object> userIndexInfo = authService.getUserIndexInfo();

            //用户信息为空，提示账号没分配角色登录不进去
            if (userIndexInfo == null) {
                model.addAttribute("tips", "该用户没有角色，无法登陆");
                return "/login.html";
            } else {
                model.addAllAttributes(userIndexInfo);
                return "/index.html";
            }
        } else {
            return "/login.html";
        }*/
        LoginUser token = sessionManager.getSession("token");
        return  new ModelAndView("/login");
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        if (LoginContextHolder.getContext().hasLogin()) {
            return new ModelAndView("/");
        } else {
            return new ModelAndView("/index");
        }
    }

    /**
     * 点击登录执行的动作
     * //ResponseData
     */
    @RequestMapping (value = "/login")
        public ModelAndView loginVali(@RequestParam String username, @RequestParam String password) {

        if (ToolUtil.isOneEmpty(username, password)) {
            throw new RequestEmptyException("账号或密码为空！");
        }

        //验证验证码是否正确
        if (ConstantsContext.getKaptchaOpen()) {
            String kaptcha = Objects.requireNonNull(HttpContext.getRequest()).getParameter("kaptcha").trim();
            String code = (String) Objects.requireNonNull(HttpContext.getRequest()).getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                throw new InvalidKaptchaException();
            }
        }

        //登录并创建token
        String token = authService.login(username, password);
        LoginUser loginUser=new LoginUser();
        loginUser.setToken(token);
        sessionManager.createSession("token", loginUser);
        //return new SuccessResponseData(token);
        return new ModelAndView("/index");
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public ResponseData logOut() {
        authService.logout();
        return new SuccessResponseData();
    }


}

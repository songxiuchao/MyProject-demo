package com.example.demo.JWTSecurity.service.Impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.roses.core.util.HttpContext;
import com.example.demo.ElasticSearch.util.SaltUtil;
import com.example.demo.JWTSecurity.Enum.AuthExceptionEnum;
import com.example.demo.JWTSecurity.Enum.ManagerStatus;
import com.example.demo.JWTSecurity.cache.SessionManager;
import com.example.demo.JWTSecurity.context.LoginContextHolder;
import com.example.demo.JWTSecurity.dao.AuthDao;
import com.example.demo.JWTSecurity.exception.AuthException;
import com.example.demo.JWTSecurity.listener.ConfigListener;
import com.example.demo.JWTSecurity.po.JwtPayLoad;
import com.example.demo.JWTSecurity.po.LoginUser;
import com.example.demo.JWTSecurity.po.User;
import com.example.demo.JWTSecurity.service.AuthService;
import com.example.demo.JWTSecurity.util.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static cn.stylefeng.roses.core.util.HttpContext.getIp;
import static com.example.demo.JWTSecurity.constants.ConstantsContext.getJwtSecretExpireSec;
import static com.example.demo.JWTSecurity.constants.ConstantsContext.getTokenHeaderName;

/**
 * @program: demo
 * @description: 逻辑处理类
 * @author: xiuchao Song
 * @create: 2019-11-05 15:43
 **/
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthDao authDao;

    @Resource
    private SessionManager sessionManager;



    @Override
    public boolean checkAll() {
        HttpServletRequest request = HttpContext.getRequest();
        LoginUser user = LoginContextHolder.getContext().getUser();
        if (null == user) {
            return false;
        }
        String requestURI = request.getRequestURI().replaceFirst(ConfigListener.getConf().get("contextPath"), "");
        String[] str = requestURI.split("/");
        if (str.length > 3) {
            requestURI = "/" + str[1] + "/" + str[2];
        }
        if (LoginContextHolder.getContext().hasPermission(requestURI)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean check(String[] roleNames) {
        //获取用户信息
        LoginUser user = LoginContextHolder.getContext().getUser();
        if (null == user) {
            return false;
        }
        //转成ArrayList
        ArrayList<String> objects = CollectionUtil.newArrayList(roleNames);
        //用StringBuilder拼成字符串
        String join = CollectionUtil.join(objects, ",");
        if (LoginContextHolder.getContext().hasAnyRoles(join)) {
            return true;
        }
        return false;
    }

    @Override
    public String login(String username, String password) {
        User user=new User();
        user.setPassword("17db03c22596b7609c7c9704f16663e0");
        user.setSalt("abcdef");
        //User user = authDao.getByAccount(username);

        // 账号不存在
        if (null == user) {
            throw new AuthException(AuthExceptionEnum.USERNAME_PWD_ERROR);
        }

        //验证账号密码是否正确
        String requestMd5 = SaltUtil.md5Encrypt(password, user.getSalt());
        String dbMd5 = user.getPassword();
        if (dbMd5 == null || !dbMd5.equalsIgnoreCase(requestMd5)) {
            throw new AuthException(AuthExceptionEnum.USERNAME_PWD_ERROR);
        }

        return login(username);
    }

    @Override
    public String login(String username) {

        User user=new User();
        user.setPassword("17db03c22596b7609c7c9704f16663e0");
        user.setSalt("abcdef");
        user.setAccount("admin");
        user.setUserId((long) 1);
        user.setStatus("ENABLE");
       // User user = authDao.getByAccount(username);
        // 账号不存在
        if (null == user) {
            throw new AuthException(AuthExceptionEnum.USERNAME_PWD_ERROR);
        }
        // 账号被冻结
        if (!user.getStatus().equals(ManagerStatus.OK.getCode())) {
            throw new AuthException(AuthExceptionEnum.ACCOUNT_FREEZE_ERROR);
        }
        //记录登录日志
        //monggodb记载，这里忽略.后续补上
        //TODO key的作用
        JwtPayLoad payLoad = new JwtPayLoad(user.getUserId(), user.getAccount(), "songxiuchao");
        //创建token
        String token = JwtTokenUtil.generateToken(payLoad);

        //创建登录会话
        //sessionManager.createSession(token, user(username));
        LoginUser loginUser=new LoginUser();
        loginUser.setAccount("admin");
        loginUser.setName("sxc");
        loginUser.setSalt("abcdef");
        loginUser.setPassword("17db03c22596b7609c7c9704f16663e0");
        loginUser.setUserId((long) 1);
        loginUser.setStatus("ENABLE");
        List<String> list=new ArrayList<>();
        list.add("administrator");
        loginUser.setRoleTips(list);
        sessionManager.createSession(token, loginUser);

        //创建cookie
        addLoginCookie(token);

        return token;
    }

    public void addLoginCookie(String token) {
        //创建cookie
        Cookie authorization = new Cookie(getTokenHeaderName(), token);
        authorization.setMaxAge(getJwtSecretExpireSec().intValue());
        authorization.setHttpOnly(true);
        authorization.setPath("/");
        HttpServletResponse response = HttpContext.getResponse();
        response.addCookie(authorization);
    }

    @Override
    public LoginUser user(String account) {

        User user = authDao.getByAccount(account);


        return null;
    }

    @Override
    public Map<String, Object> getUserIndexInfo() {
        return null;
    }

    @Override
    public void logout() {
        String token = LoginContextHolder.getContext().getToken();
        logout(token);
    }

    public void logout(String token) {

        //记录退出日志
        //略掉

        //删除Auth相关cookies
        Cookie[] cookies = HttpContext.getRequest().getCookies();
        for (Cookie cookie : cookies) {
            String tokenHeader = getTokenHeaderName();
            if (tokenHeader.equalsIgnoreCase(cookie.getName())) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                HttpContext.getResponse().addCookie(temp);
            }
        }

        //删除会话
        sessionManager.removeSession(token);
    }

}

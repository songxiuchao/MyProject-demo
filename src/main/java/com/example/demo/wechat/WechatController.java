package com.example.demo.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Common.constant.BaseUtil;
import com.example.demo.Common.constant.ResponseCode;
import com.example.demo.Common.constant.Result;
import com.example.demo.wechat.Login.vo.Oauth2Token;
import com.example.demo.wechat.Login.vo.SNSUserInfo;
import com.example.demo.wechat.menu.MenuHandlerUtil;
import com.example.demo.wechat.menu.MenuManager;
import com.example.demo.wechat.menu.vo.MenuListVo;
import com.example.demo.wechat.menu.vo.MenuVo;
import com.example.demo.wechat.message.MessageHandlerUtil;
import com.example.demo.wechat.utils.AccessToken;
import com.example.demo.wechat.utils.NetUtil;
import com.example.demo.wechat.utils.WechatTool;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 微信公众号Controller
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Value("${URL_GET_OPENID}")
    private String URL_GET_OPENID;
    @Value("${URL_LOGIN}")
    private String URL_LOGIN;

    /**
     * 进入主页面
     */
    @RequestMapping({"/index"})
    public String index() {
        return "Wechat-Main";
    }

    /**
     * 验证消息来自微信服务器
     * <p>
     * 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，开发者通过检验signature对请求进行校验。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者。否则接入失败！
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("开始校验签名...");

        // 微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码
        request.setCharacterEncoding("UTF-8");
        // 在响应消息（回复消息给用户）时，也将编码方式设置为UTF
        response.setCharacterEncoding("UTF-8");

        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        log.info("----------------------------------------");
        log.info("signature--" + signature);
        log.info("timestamp--" + timestamp);
        log.info("nonce--" + nonce);
        log.info("echostr--" + echostr);
        log.info("----------------------------------------");

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WechatTool.checkSignature(signature, WechatConfig.TOKEN, timestamp, nonce)) {
            log.info("签名校验通过!");
            response.getWriter().write(echostr);
        } else {
            log.info("签名校验失败!!!");
        }
    }

    /**
     * 处理微信服务器发来的消息
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO 接收、处理、响应由微信服务器转发的用户发送给公众帐号的消息
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String result = "";
        try {
            // 把微信发来的请求（XML）格式数据，转换为Map<String, String>格式
            Map<String, String> mapRequestData = MessageHandlerUtil.parseXml(request);

            // 根据消息类型构造返回消息
            result = MessageHandlerUtil.buildResponseMessage(mapRequestData);
            response.getWriter().println(result);
        } catch (Exception e) {
            result = "解析或处理消息出现异常！";
            log.error(result);
            response.getWriter().println(result);
        }
    }

    /************************************************************
     * 获取code（是获取openid之前的必要步骤）
     */
    @RequestMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response, @RequestParam("appId") String appId) {
        StringBuffer sbuUrl = new StringBuffer(WechatConfig.URL_GET_CODE);
        sbuUrl.append("?appid=").append(appId);
        sbuUrl.append("&redirect_uri=").append(URL_GET_OPENID);
        sbuUrl.append("&response_type=code");
        sbuUrl.append("&scope=snsapi_base");
        sbuUrl.append("&state=").append(appId);
        sbuUrl.append("#wechat_redirect");

        try {
            log.info("执行跳转: " + sbuUrl.toString());
            response.sendRedirect(sbuUrl.toString());
        } catch (UnsupportedEncodingException e) {
            log.error("重定向url编码失败：>>" + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            log.error("response重定向失败：>>" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取openId
     */
    @RequestMapping("/getOpenId")
    @ResponseBody
    public String getOpenId(HttpServletRequest request, HttpServletResponse response) {
        log.info("进入getOpenId...");

        // 获取重定向携带的code参数值
        String code = request.getParameter("code");
        String appId = request.getParameter("state");
        String appSecret = "bdf021d28aac723e8302e95f00aeafc0";
        log.info("code is: " + code);

        // 发送请求，根据code获取openId
        String openid = getOpenIdByCode(request, response, appId, appSecret, code);
        log.info("openid is: " + openid);
        return openid;
    }

    /************************************************************
     * 发送模板消息（主动调用）
     */
    @RequestMapping("/sendTemplateMessage")
    public Result sendTemplateMessage(@RequestParam("faultTypeName") String faultTypeName,
                                      @RequestParam("tenantId") Long tenantId,
                                      @RequestParam("equipmentNo") String equipmentNo,
                                      @RequestParam("openIds") String openIds,
                                      @RequestParam("msgContent") String msgContent) {
        //获取appid和appSecret

        //模板ID
        String templateId = "";
        if(!BaseUtil.isEmpty(templateId)) {
            if(!BaseUtil.isEmpty(openIds)) {
                String[] arysOpenId = null;
                // 如果有多个openId
                if(openIds.contains(",")) {
                    //分割解析openId
                    arysOpenId = openIds.split(",");
                } else {
                	// 只有一个openId
                    arysOpenId = new String[1];
                    arysOpenId[0] = openIds;
                }
            } else {
                Result.failure(ResponseCode.ERROR_PARAMS_VALIDATOR, "接收者ID（openId）为空");
            }
        } else {
            return Result.failure(ResponseCode.ERROR_PARAMS_VALIDATOR, "消息模板ID为空");
        }
        return Result.success("发送成功");
    }

    /************************************************************
     * 创建自定义菜单
     * @param menuListVo
     */
    @ApiOperation(value = "创建自定义菜单", notes = "创建自定义菜单")
    @PostMapping("/creatMenu")
    public Result creatMenu(@RequestParam String menuListVo, @RequestParam String appId, @RequestParam String appSecret) {
        // 接收到的JSON字符串转换为对象
        List<MenuVo> lstMenu = JSONArray.parseArray(menuListVo, MenuVo.class);

        MenuListVo menuList = new MenuListVo();
        menuList.setButton(lstMenu);

        // 调用接口获取access_token
        AccessToken token = WechatTool.getToken(appId, appSecret);
        int result = 0;

        if (null != token) {
            // 调用接口创建菜单
            MenuManager manager = new MenuManager();
            result = MenuHandlerUtil.createMenu(manager.getMenu(menuList), token.getAccessToken());
            // 判断菜单创建结果
            if (0 == result) {
                return Result.success("创建成功");
            }
        }
        return Result.failure("菜单创建失败，错误码：" + result, ResponseCode.ERROR_999);
    }

    /**
     * 查询自定义菜单
     *
     * @param appId
     */
    @ApiOperation(value = "查询自定义菜单", notes = "查询自定义菜单")
    @GetMapping("/getMenu")
    public Result getMenuList(@RequestParam("appId") String appId) {
        String appSecret = WechatConfig.APPSECRET;

        // 调用接口获取access_token
        AccessToken token = WechatTool.getToken(appId, appSecret);
        JSONObject result = null;

        if (null != token) {
            // 调用接口创建菜单
            result = MenuHandlerUtil.getMenu(token.getAccessToken());
            // 判断菜单创建结果
            if (result == null) {
                log.error("菜单查询失败，错误码：" + result);
                return Result.failure(ResponseCode.ERROR_999, "菜单查询失败，错误码：" + result);
            }
        }
        return Result.success("查询成功", result);
    }

    /**
     * 删除自定义菜单
     */
    @ApiOperation(value = "删除自定义菜单", notes = "删除自定义菜单")
    @GetMapping("/deleteMenu")
    public Result deleteMenu(@RequestParam("appId") String appId) {
        String appSecret = WechatConfig.APPSECRET;

        // 调用接口获取access_token
        AccessToken token = WechatTool.getToken(appId, appSecret);
        JSONObject result = null;

        if (null != token) {
            // 调用接口创建菜单
            result = MenuHandlerUtil.deleteMenu(token.getAccessToken());
            // 判断菜单创建结果
            if (result == null) {
                log.error("菜单删除失败，错误码：" + result);
                return Result.failure(ResponseCode.ERROR_999, "菜单删除失败，错误码：" + result);
            }

        }
        return Result.success("删除成功", result);
    }

    /************************************************************
     * 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 向指定URL发送GET方法的请求 url发送请求的URL
     * 用户同意授权，获取code
     */
    @GetMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response, @RequestParam("appId") String appId)
            throws ServletException, IOException {
        StringBuffer sbuUrl = new StringBuffer(WechatConfig.URL_GET_CODE);
        sbuUrl.append("?appid=").append(appId);
        sbuUrl.append("&redirect_uri=").append(URL_LOGIN);
        sbuUrl.append("&response_type=code");
        sbuUrl.append("&scope=snsapi_userinfo");
        sbuUrl.append("&state=").append(appId);
        sbuUrl.append("#wechat_redirect");
        try {
            response.sendRedirect(sbuUrl.toString());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 微信授权登录
     */
    @GetMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 用户同意授权后，能获取到code
        Map<String, String[]> params = request.getParameterMap();
        String[] codes = params.get("code");
        String code = codes[0];

        // 用户同意授权
        if (!"authdeny".equals(code)) {
            // 获取网页授权access_token
            Oauth2Token oauth2Token = getOauth2AccessToken(WechatConfig.APPID, WechatConfig.APPSECRET, code);
            if (oauth2Token != null) {
                // 网页授权接口访问凭证
                String accessToken = oauth2Token.getAccessToken();
                // 用户标识
                String openId = oauth2Token.getOpenId();
                // 获取用户信息
                SNSUserInfo snsUserInfo = getSNSUserInfo(accessToken, openId);

                // 执行业务操作
                if (snsUserInfo != null) {
                    System.out.println("******************************");
                    System.out.println("用户信息: " + snsUserInfo.toString());
                    System.out.println("******************************");
                }
            }

            String url = "http://www.baidu.com";
            response.sendRedirect(url);
        }
    }

    /**
     * 获取网页授权凭证
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static Oauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        Oauth2Token token = null;

        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);

        // 获取网页授权凭证
        JSONObject jsonObject = JSON.parseObject(NetUtil.get(requestUrl));
        if (null != jsonObject) {
            try {
                token = new Oauth2Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInteger("expires_in"));
                token.setRefreshToken(jsonObject.getString("refresh_token"));
                token.setOpenId(jsonObject.getString("openid"));
                token.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return token;
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return SNSUserInfo
     */
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;

        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        // 通过网页授权获取用户信息
        JSONObject jsonObject = JSON.parseObject(NetUtil.get(requestUrl));
        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInteger("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                List<String> list = JSON.parseArray(jsonObject.getString("privilege"), String.class);
                snsUserInfo.setPrivilegeList(list);
                // 与开放平台共用的唯一标识，只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
                snsUserInfo.setUnionid(jsonObject.getString("unionid"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }

    /***************************************************
     * 发送请求：通过code获取openId
     */
    @SuppressWarnings("unchecked")
    private String getOpenIdByCode(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam("appId") String appId, @RequestParam("appSecret") String appSecret,
                                   @RequestParam("code") String code) {
        String content = "";
        String openId = "";
        String unionId = "";

        // 封装获取openId的微信API
        StringBuffer url = new StringBuffer();
        url.append("https://api.weixin.qq.com/sns/oauth2/access_token");
        url.append("?appid=").append(appId);
        url.append("&secret=").append(appSecret);
        url.append("&code=").append(code);
        url.append("&grant_type=authorization_code");

        content = WechatTool.httpsRequest(url.toString(), "POST", null);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> map = objectMapper.readValue(content, Map.class);
            openId = String.valueOf(map.get("openid"));
            unionId = String.valueOf(map.get("unionid"));

            System.out.println("------------------------------");
            Iterator<String> itKeys = map.keySet().iterator();
            while (itKeys.hasNext()) {
                String key = itKeys.next();
                System.out.println(key + "----" + map.get(key).toString());
            }
            System.out.println("------------------------------");

        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return openId;
    }


    /**
     * URL编码（utf-8）
     *
     * @param url
     * @return String
     */
    public static String urlEncodeUTF8(String url) {
        String result = url;
        try {
            result = URLEncoder.encode(url, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
}
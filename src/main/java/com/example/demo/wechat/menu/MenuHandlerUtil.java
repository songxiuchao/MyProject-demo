package com.example.demo.wechat.menu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.wechat.menu.vo.Menu;
import com.example.demo.wechat.utils.WechatTool;
import lombok.extern.slf4j.Slf4j;

/**
 * 菜单处理工具类
 * @author snps
 * 2019年8月19日上午9:51:42
 */
@Slf4j
public class MenuHandlerUtil {
	
	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
    static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 获得菜单
     */
    static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    
    /**
     * 删除菜单
     */
    static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    
    
    /**
     * 创建菜单
     * @param menu 菜单实例
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static int createMenu(Menu menu, String accessToken) {
    	int result = 0;

    	// 拼装创建菜单的url
    	String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
    	// 将菜单对象转换成json字符串
    	String jsonMenu = JSON.toJSONString(menu);
        // 调用接口创建菜单
    	String response = WechatTool.httpsRequest(url, "POST", jsonMenu);
    	JSONObject jsonObject = JSON.parseObject(response);
        if (null != jsonObject) {
            if (0 != jsonObject.getInteger("errcode")) {
                result = jsonObject.getInteger("errcode");
                log.error("创建菜单失败 errcode:{} errmsg:{}",
                		jsonObject.getInteger("errcode"), jsonObject.getString("errmsg"));
            }
        }

        return result;
    }

    /**
     * 获得菜单
     * @param accessToken 有效的access_token
     * @return JSONObject
     */
    public static JSONObject getMenu(String accessToken) {
        // 拼装创建菜单的url
        String url = MENU_GET_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSON(new Menu()).toString();
        // 调用接口查询菜单
        String response = WechatTool.httpsRequest(url, "GET", jsonMenu);
    	JSONObject jsonObject = JSON.parseObject(response);
        return jsonObject;
    }

    /**
     * 删除菜单
     * @param accessToken 有效的access_token
     * @return 0表示成功，其他值表示失败
     */
    public static JSONObject deleteMenu( String accessToken) {
        // 拼装创建菜单的url
        String url = MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.toJSON(new Menu()).toString();
        // 调用接口查询菜单
        String response = WechatTool.httpsRequest(url, "GET", jsonMenu);
    	JSONObject jsonObject = JSON.parseObject(response);
        return jsonObject;
    }
	
}
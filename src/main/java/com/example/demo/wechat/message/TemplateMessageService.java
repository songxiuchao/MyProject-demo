package com.example.demo.wechat.message;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.wechat.WechatConfig;
import com.example.demo.wechat.message.vo.TemplateData;
import com.example.demo.wechat.message.vo.WechatTemplate;
import com.example.demo.wechat.utils.AccessToken;
import com.example.demo.wechat.utils.WechatTool;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 模板消息Service
 *
 * @author snps 2019年7月25日下午4:01:44
 */
@Service
public class TemplateMessageService {

    /**
     * 创建消息
     *
     * @param openId
     * @param templateId 消息模板Id
     * @param url        跳转地址
     * @param tenantEquipmentInfo        设备信息
     * @param msgContent        消息内容
     * @return WechatTemplate 模板消息
     */
    public WechatTemplate createMessage(String openId, String templateId, String url,  String msgContent, String faultTypeName) {

        // TODO 后期把这里改用动态数据实现
        // 构造模板消息数据
        TemplateData data_first = new TemplateData();
        data_first.setValue(faultTypeName);
        data_first.setColor("#173177");

        TemplateData data_keyword1 = new TemplateData();
        data_keyword1.setColor("#173177");

        TemplateData data_keyword2 = new TemplateData();
        data_keyword2.setColor("#173177");

        TemplateData data_keyword3 = new TemplateData();
        data_keyword3.setColor("#173177");

        TemplateData data_keyword4 = new TemplateData();
        data_keyword4.setColor("#173177");
        TemplateData data_keyword5 = new TemplateData();
        data_keyword5.setValue(msgContent);
        data_keyword5.setColor("#173177");

        TemplateData data_remark = new TemplateData();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        data_remark.setValue("日期："+ft.format(dNow));
        data_remark.setColor("#173177");

        Map<String, TemplateData> messageData = new HashMap<String, TemplateData>();
        messageData.put("first", data_first);
        messageData.put("keyword1", data_keyword1);
        messageData.put("keyword2", data_keyword2);
        messageData.put("keyword3", data_keyword3);
        messageData.put("keyword4", data_keyword4);
        messageData.put("keyword5", data_keyword5);
        messageData.put("remark", data_remark);

        // 封装模板消息
        WechatTemplate template = new WechatTemplate();
        template.setTouser(openId);
        template.setTemplate_id(templateId);
        template.setTopcolor("#000000");
        template.setData(messageData);
        template.setUrl(url);
        return template;
    }

    /**
     * 发送消息
     *
     * @param appId       微信平台设置的-开发者ID(AppID)
     * @param appSecret   微信平台设置的-开发者密码(AppSecret)
     * @param templateMsg 消息模板
     */
    public void send(String appId, String appSecret, WechatTemplate templateMsg) {
        AccessToken token = WechatTool.getToken(appId, appSecret);
        String url = WechatConfig.TEMPLATE_MSG_URL.replace("ACCESS_TOKEN", token.getAccessToken());
        String jsonData = JSONObject.toJSONString(templateMsg);
        WechatTool.httpsRequest(url, "POST", jsonData);
    }

}
package com.example.demo.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenAuthTokenAppRequest;
import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.request.AlipayTradeRoyaltyRelationBindRequest;
import com.alipay.api.response.AlipayOpenAuthTokenAppResponse;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import com.alipay.api.response.AlipayTradeRoyaltyRelationBindResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @program: demo
 * @description: 支付宝分账测试类
 * @author: xiuchao Song
 * @create: 2019-11-16 09:49
 **/
@RestController
@RequestMapping("/settle")
@Slf4j
public class AlipaySettleTset {
    /**
     * 授权
     * @param httpResponse
     * @throws Exception
     */
    @RequestMapping(value = "/beforeInit", method = RequestMethod.GET)
    public void beforeInit(HttpServletResponse httpResponse) throws Exception{
        //获取支付宝appId  http://dev.yohibox.com/service/yohi-sale/init
        String returnUrl = "http://test.yohibox.com/settle/initReturn";
        String a="http://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id="+AliPayContant.ALI_APP_ID+"&redirect_uri="+returnUrl;
        String url = httpResponse.encodeRedirectURL(a);
        System.out.println(url);
        httpResponse.sendRedirect(httpResponse.encodeRedirectURL(url));
    }

    /**
     * 授权初始化回调
     * @throws Exception
     */
    @RequestMapping(value = "/initReturn", method = RequestMethod.GET)
    public String initReturn(@RequestParam(required = false) String app_auth_code ,
                           @RequestParam(required = false) String app_id) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL, app_id,AliPayContant.ALI_PRIVITE_KEY,
                AliPayContant.ALI_OBJECT, AliPayContant.ALI_CHARSET, AliPayContant.ALI_PUBLIC_KEY,AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        //必传的请求参数
        request.setBizContent(
                "{" +"\"grant_type\":\"authorization_code\"," +"\"code\":"+"\""+app_auth_code+"\""+" }");
        try{
            AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
            String appAuthToken = response.getAppAuthToken();
            if (response.getMsg().equals("Success")){
                System.out.println("appAuthToken=    "+appAuthToken);
                //同步返回的结果
                System.out.println(response.getBody());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Success";
    }


    /**
     * 绑定关系
     */
    @RequestMapping("/bind")
   public String bind(@RequestParam String type,@RequestParam String account,
                    @RequestParam String name,@RequestParam String memo,@RequestParam String outRequestNo,
                    @RequestParam String appAuthToken) throws AlipayApiException {
       AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL,AliPayContant.ALI_APP_ID,AliPayContant.ALI_PRIVITE_KEY,
               AliPayContant.ALI_OBJECT, "GBK", AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
       AlipayTradeRoyaltyRelationBindRequest request = new AlipayTradeRoyaltyRelationBindRequest();
       request.setBizContent("{" +
               "      \"receiver_list\":[{" +
               "        \"type\":"+"\""+type+"\""+","+
               "\"account\":"+"\""+account+"\""+"," +
               "\"name\":"+"\""+name+"\""+"," +
               "\"memo\":" +"\""+memo+"\""+
               "        }]," +
               "\"out_request_no\":"+"\""+outRequestNo +"\""+
               "  }");
       AlipayTradeRoyaltyRelationBindResponse response = alipayClient.execute(request,null,appAuthToken);
       if(response.isSuccess()){
          return "Success";
       } else {
           return "ERROR";
       }
   }




    /**
     * 转账
     * @throws AlipayApiException
     */
    @PostMapping("/orderSettle")
    public void orderSettle(@RequestParam String outRequestNo,@RequestParam  String tradeNo,@RequestParam String royaltyType,
                            @RequestParam String transOut,@RequestParam String transOutType,@RequestParam String transInType,
                            @RequestParam String transIn,@RequestParam String amount,@RequestParam String operatorId)throws AlipayApiException{
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL, AliPayContant.ALI_APP_ID,
                AliPayContant.ALI_PRIVITE_KEY, AliPayContant.ALI_OBJECT,"GBK",
                AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
        request.setBizContent("{" +
                "\"out_request_no\":"+outRequestNo +","+
                "\"trade_no\":"+tradeNo+"," +
                "\"royalty_parameters\":[{" +
                "\"royalty_type\":"+royaltyType+"," +
                "\"trans_out\":"+transOut+"," +
                "\"trans_out_type\":"+transOutType+"," +
                "\"trans_in_type\":"+transInType+"," +
                "\"trans_in\":"+transIn+"," +
                "\"amount\":"+amount+"," +
                "\"desc\":"+"分帐给" +transIn+" "+amount+"元"+
                "        }]," +
                "\"operator_id\":"+operatorId +
                "  }");
        AlipayTradeOrderSettleResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 刷新令牌同
     * @param refresh_token
     * @throws AlipayApiException
     */
    @RequestMapping("/refresh")
    public void refresh(@RequestParam String refresh_token) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL,AliPayContant.ALI_APP_ID,AliPayContant.ALI_PRIVITE_KEY,
                AliPayContant.ALI_OBJECT, "GBK", AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayOpenAuthTokenAppRequest request = new AlipayOpenAuthTokenAppRequest();
        request.setBizContent("{" + "\"grant_type\":\"refresh_token \"," +" " + "   \" refresh_token\":"+"\""+refresh_token+"\""
                + "  }");
        AlipayOpenAuthTokenAppResponse response = alipayClient.execute(request);
        System.out.println("AppAuthToken:       "+response.getAppAuthToken());
    }

}

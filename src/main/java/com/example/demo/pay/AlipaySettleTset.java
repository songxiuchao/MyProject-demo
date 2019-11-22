package com.example.demo.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
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
        AlipayTradeRoyaltyRelationBindResponse  response = alipayClient.execute(request,"",appAuthToken);
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
     * 统一收单交易退款接口
     * @throws AlipayApiException
     */
    @RequestMapping("/refund")
    public void refund(@RequestParam String outTradeNo,@RequestParam String tradeNo,@RequestParam String refundAmount,
                       @RequestParam String refundCurrency,@RequestParam String refundReason,@RequestParam String outRequestNo,
                       @RequestParam String operatorId,@RequestParam String storeId,@RequestParam String terminalId,
                       @RequestParam String goodsId,@RequestParam String alipayGoodsId,@RequestParam String goodsName,
                       @RequestParam String quantity,@RequestParam String price,@RequestParam String goodsCategory,
                       @RequestParam String categoriesTree,@RequestParam String body ,@RequestParam String showUrl,
                       @RequestParam String royaltyType,@RequestParam String transOut,@RequestParam String transOutType,
                       @RequestParam String transInType,@RequestParam String transIn,@RequestParam String amount,
                       @RequestParam String amountPercentage,@RequestParam String desc,@RequestParam String orgPid) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":"+"\""+outTradeNo+"\""+"," +
                "\"trade_no\":"+"\""+tradeNo+"\""+"," +
                "\"refund_amount\":"+"\""+refundAmount+"\""+"," +
                "\"refund_currency\":"+"\""+refundCurrency+"\""+"," +
                "\"refund_reason\":"+"\""+refundReason+","+"\"" +
                "\"out_request_no\":"+"\""+outRequestNo+","+"\"" +
                "\"operator_id\":"+"\""+operatorId+"\""+"," +
                "\"store_id\":"+"\""+storeId+"\""+"," +
                "\"terminal_id\":"+"\""+terminalId+"\""+"," +
                "      \"goods_detail\":[{" +
                "        \"goods_id\":"+"\""+goodsId+"\""+"," +
                "\"alipay_goods_id\":"+"\""+alipayGoodsId+"\""+"," +
                "\"goods_name\":"+"\""+goodsName+"\""+"," +
                "\"quantity\":"+"\""+quantity+"\""+"," +
                "\"price\":"+"\""+price+"\""+"," +
                "\"goods_category\":"+"\""+goodsCategory+"\""+"," +
                "\"categories_tree\":"+"\""+categoriesTree+"\""+"," +
                "\"body\":"+"\""+body+"," +"\""+
                "\"show_url\":"+"\""+showUrl+"\"" +
                "        }]," +
                "      \"refund_royalty_parameters\":[{" +
                "        \"royalty_type\":"+"\""+royaltyType+"\""+"," +
                "\"trans_out\":"+"\""+transOut+"\""+"," +
                "\"trans_out_type\":"+"\""+transOutType+"\""+"," +
                "\"trans_in_type\":"+"\""+transInType+"\""+"," +
                "\"trans_in\":"+"\""+transIn+"\""+"," +
                "\"amount\":"+"\""+amount+"\""+"," +
                "\"amount_percentage\":"+"\""+amountPercentage +"\""+","+
                "\"desc\":"+desc +
                "        }]," +
                "\"org_pid\":"+orgPid +
                "  }");
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 分账查询
     * @param outTradeNo
     * @param tradeNo
     * @param orgPid
     * @param queryOptions
     * @throws AlipayApiException
     */
    @RequestMapping("/query")
    public void query(@RequestParam String outTradeNo,@RequestParam String tradeNo,@RequestParam String orgPid,
                      @RequestParam String queryOptions) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":"+"\""+outTradeNo+"\""+"," +
                "\"trade_no\":"+"\""+tradeNo+"\""+"," +
                "\"org_pid\":"+"\""+orgPid+"\""+"," +
                "      \"query_options\":[" + "\""+queryOptions+"\""+ "]" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    /**
     * 解除绑定
     * @param type
     * @param account
     * @param name
     * @param memo
     * @param outRequestNo
     * @throws AlipayApiException
     */
    @RequestMapping("/unbind")
    public void unbind(@RequestParam String type,@RequestParam String account,@RequestParam String name,
                       @RequestParam String memo,@RequestParam String outRequestNo) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","app_id","your private_key","json","GBK","alipay_public_key","RSA2");
        AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
        request.setBizContent("{" +
                "      \"receiver_list\":[{" +
                "        \"type\":"+"\""+type+"\""+"," +
                "\"account\":"+"\""+account+"\""+"," +
                "\"name\":"+name+"," +
                "\"memo\":"+"\""+memo+"\""+
                "        }]," +
                "\"out_request_no\":"+"\""+outRequestNo+"\"" +
                "  }");
        AlipayTradeRoyaltyRelationUnbindResponse response = alipayClient.execute(request);
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

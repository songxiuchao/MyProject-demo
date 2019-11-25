package com.example.demo.Settle;

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
                    @RequestParam(required = false) String appAuthToken) throws AlipayApiException {
       AlipayClient alipayClient = new DefaultAlipayClient(
                AliPayContant.ALI_GETAWAY_URL,
                AliPayContant.ALI_APP_ID,
                AliPayContant.ALI_PRIVITE_KEY,
                AliPayContant.ALI_OBJECT,
               "GBK",
                AliPayContant.ALI_PUBLIC_KEY,
               AliPayContant.ALI_SIGN_TYPE_RSA2);
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
        //AlipayTradeRoyaltyRelationBindResponse  response = alipayClient.execute(request,"",appAuthToken);  //开发者代替商户发起请求时，
        AlipayTradeRoyaltyRelationBindResponse  response = alipayClient.execute(request);
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
    public String orderSettle(@RequestParam String outRequestNo,@RequestParam  String tradeNo,@RequestParam(required = false) String royaltyType,
                            @RequestParam(required = false) String transOut,@RequestParam(required = false) String transOutType,
                            @RequestParam(required = false) String transInType, @RequestParam(required = false) String transIn,
                            @RequestParam(required = false) String amount,@RequestParam(required = false) String operatorId,
                            @RequestParam(required = false) String desc,@RequestParam(required = false) String appAuthToken)throws AlipayApiException{
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL,AliPayContant.ALI_APP_ID,AliPayContant.ALI_PRIVITE_KEY,
                AliPayContant.ALI_OBJECT, "GBK", AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayTradeOrderSettleRequest request = new AlipayTradeOrderSettleRequest();
            request.setBizContent("{" +
                    "\"out_request_no\":"+"\""+outRequestNo +"\""+","+
                    "\"trade_no\":"+"\""+tradeNo+"\""+"," +
                    "\"royalty_parameters\":[{" +
                    "\"royalty_type\":"+"\""+royaltyType+"\""+"," +
                    "\"trans_out\":"+"\""+transOut+"\""+"," +
                    "\"trans_out_type\":"+"\""+transOutType+"\""+"," +
                    "\"trans_in_type\":"+"\""+transInType+"\""+"," +
                    "\"trans_in\":"+"\""+transIn+"\""+"," +
                    "\"amount\":"+amount+"," +
                    "\"desc\":"+"\""+desc+"\""+"}]"+"}");
        //AlipayTradeOrderSettleResponse response = alipayClient.execute(request,"",appAuthToken);
        System.out.println(request.getBizContent());
        AlipayTradeOrderSettleResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if(response.isSuccess()){
            return "Success";
        } else {
            return "ERROR";
        }
    }

    /**
     * 统一收单交易退款接口
     * @throws AlipayApiException
     */
    @RequestMapping("/refund")
    public String refund(@RequestParam(required =false) String outTradeNo,@RequestParam(required = false) String tradeNo,
                       @RequestParam(required = false) String refundAmount,
                       @RequestParam(required = false) String refundCurrency,@RequestParam String refundReason,@RequestParam String outRequestNo,
                       @RequestParam(required = false) String operatorId,@RequestParam String storeId,@RequestParam String terminalId,
                       @RequestParam String goodsId,@RequestParam(required = false) String alipayGoodsId,@RequestParam String goodsName,
                       @RequestParam String quantity,@RequestParam String price,@RequestParam(required = false) String goodsCategory,
                       @RequestParam (required = false)String categoriesTree,@RequestParam(required = false) String body ,
                       @RequestParam(required = false) String showUrl, @RequestParam(required = false) String royaltyType,
                       @RequestParam(required = false) String transOut,@RequestParam(required = false) String transOutType,
                       @RequestParam(required = false) String transInType,@RequestParam(required = false) String transIn,
                       @RequestParam(required = false) String amount, @RequestParam(required = false) String amountPercentage,
                       @RequestParam(required = false) String desc,@RequestParam(required = false) String orgPid) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL,AliPayContant.ALI_APP_ID,AliPayContant.ALI_PRIVITE_KEY,
                AliPayContant.ALI_OBJECT, "GBK", AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"trade_no\":"+"\""+tradeNo+"\""+"," +
                "\"refund_amount\":"+"\""+refundAmount+"\""+
                "        }]" +
                "  }");
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        if(response.isSuccess()){
            return "Success";
        } else {
            return "ERROR";
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
    public String query(@RequestParam(required = false) String outTradeNo,@RequestParam(required = false)  String tradeNo,
                      @RequestParam(required = false) String orgPid,
                      @RequestParam(required = false) String queryOptions) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL, AliPayContant.ALI_APP_ID,
                AliPayContant.ALI_PRIVITE_KEY, AliPayContant.ALI_OBJECT,"GBK",
                AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":"+"\""+outTradeNo+"\""+"," +
                "\"trade_no\":"+"\""+tradeNo+"\""+"," +
                //注掉的需要条件下使用
               // "\"trade_no\":"+"\""+tradeNo+"\""+"," +
                //"\"org_pid\":"+"\""+orgPid+"\""+"," +
                "      \"query_options\":[" + "\""+queryOptions+"\""+ "]" +
                "  }");
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        if(response.isSuccess()){
            return response.getBody();
        } else {
            return "ERROR";
        }
    }

    /**
     * 查询绑定关系
     * @param appAuthToken
     * @param outRequestNo
     * @throws AlipayApiException
     */
    @RequestMapping("/batchquery")
    public void t(@RequestParam(required = false) String appAuthToken,@RequestParam String outRequestNo) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL, AliPayContant.ALI_APP_ID,
                AliPayContant.ALI_PRIVITE_KEY, AliPayContant.ALI_OBJECT,"GBK",
                AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayTradeRoyaltyRelationBatchqueryRequest request = new AlipayTradeRoyaltyRelationBatchqueryRequest();
        request.setBizContent("{" +
                "\"page_num\":1," +
                "\"page_size\":20," +
                "\"out_request_no\":"+"\""+outRequestNo+"\"" +
                "  }");
        //AlipayTradeRoyaltyRelationBatchqueryResponse response = alipayClient.execute(request,"",appAuthToken);
        AlipayTradeRoyaltyRelationBatchqueryResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
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
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayContant.ALI_GETAWAY_URL, AliPayContant.ALI_APP_ID,
                AliPayContant.ALI_PRIVITE_KEY, AliPayContant.ALI_OBJECT,"GBK",
                AliPayContant.ALI_PUBLIC_KEY, AliPayContant.ALI_SIGN_TYPE_RSA2);
        AlipayTradeRoyaltyRelationUnbindRequest request = new AlipayTradeRoyaltyRelationUnbindRequest();
        request.setBizContent("{" +
                "      \"receiver_list\":[{" +
                "        \"type\":"+"\""+type+"\""+"," +
                "\"account\":"+"\""+account+"\""+"," +
                "\"name\":"+"\""+name+"\""+"," +
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

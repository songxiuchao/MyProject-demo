package com.example.demo.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeOrderSettleRequest;
import com.alipay.api.response.AlipayTradeOrderSettleResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description: 支付宝分账测试类
 * @author: xiuchao Song
 * @create: 2019-11-16 09:49
 **/
@RestController
@RequestMapping("/settle")
public class AlipaySettleTset {

    @PostMapping("/orderSettle")
    public void orderSettle(@RequestParam String outRequestNo,@RequestParam  String tradeNo,@RequestParam String royaltyType,
                            @RequestParam String transOut,@RequestParam String transOutType,@RequestParam String transInType,
                            @RequestParam String transIn,@RequestParam String amount,@RequestParam String desc,
                            @RequestParam String operatorId)throws AlipayApiException{
        String private_key="";
        String public_key="";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","2019090566911672",private_key,"json","GBK",public_key,"RSA2");
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
                "\"desc\":"+desc +
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
}

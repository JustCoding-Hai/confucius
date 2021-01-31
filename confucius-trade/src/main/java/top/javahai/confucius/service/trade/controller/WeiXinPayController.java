package top.javahai.confucius.service.trade.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.helper.StreamHelper;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.trade.entity.Order;
import top.javahai.confucius.service.trade.service.OrderService;
import top.javahai.confucius.service.trade.service.WeiXinPayService;
import top.javahai.confucius.service.trade.util.WeiXinPayProperties;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/28 - 18:14
 **/
@RestController
@RequestMapping("/api/trade/weixin-pay")
@Api(tags = "网站微信支付")
@Slf4j
public class WeiXinPayController {
    @Autowired
    private WeiXinPayService weixinPayService;

    @Autowired
    private WeiXinPayProperties weiXinPayProperties;

    @Autowired
    private OrderService orderService;

    @GetMapping("create-native/{orderNo}")
    public ResultVO<Map> createNative(@PathVariable String orderNo, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        Map map = weixinPayService.createNative(orderNo, remoteAddr);
        return new ResultVO<Map>().success().data(map);
    }

    /**
     * 微信支付结果回调接口
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/callback/notify")
    public String wxCallBackNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        String notifyXml = StreamHelper.inputStream2String(inputStream,"utf-8");
        System.out.println("notify xml string="+notifyXml);
        //响应对象
        HashMap<String, String> returnMap = new HashMap<>(16);
        //签名校验
        boolean signatureValid = WXPayUtil.isSignatureValid(notifyXml, weiXinPayProperties.getPartnerKey());
        //解析返回的结果
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyXml);
        if (signatureValid){
            //判断支付是否成功
            if("SUCCESS".equals(notifyMap.get("result_code"))) {
                // 获取本次支付的金额
                String totalFee = notifyMap.get("total_fee");
                String outTradeNo = notifyMap.get("out_trade_no");
                Order order = orderService.getOrderByOrderNo(outTradeNo);
                //校验支付金额和订单金额是否相等
                if (order!=null&&order.getTotalFee().intValue()==Integer.valueOf(totalFee)){
                    /*
                    判断订单的状态：保证接口调用的幂等性，如果订单状态已经更新直接返回成功响应。
                    考虑情况：处理更新完订单，但还没有响应给微信，微信又再一次回调该接口造成订单状态再次更新。
                    幂等性：无论调用多少次结果都一样
                     */
                    if(order.getStatus()==0){
                        orderService.updateOrderStatus(notifyMap);
                    }
                    returnMap.put("return_code", "SUCCESS");
                    returnMap.put("return_msg", "OK");
                    //xml格式响应
                    String returnXml = WXPayUtil.mapToXml(returnMap);
                    response.setContentType("text/xml");
                    return returnXml;
                }
            }
        }
        String errCode = notifyMap.get("err_code");
        String errCodeDes = notifyMap.get("err_code_des");
        String transactionId = notifyMap.get("transaction_id");
        String outTradeNo = notifyMap.get("out_trade_no");
        log.error("校验失败,outTradeNo:{},transactionId:{},errCode：{},errCodeDes:{}",outTradeNo,transactionId,errCode,errCodeDes);
        // 上面的校验失败，返回失败应答
        returnMap.put("return_code", "FAIL");
        returnMap.put("return_msg", "");
        String returnXml = WXPayUtil.mapToXml(returnMap);
        response.setContentType("text/xml");
        return returnXml;
    }

    @GetMapping("/query-pay-status/{orderNo}")
    public ResultVO queryPayStatus(@PathVariable String orderNo) {
        boolean result = orderService.queryPayStatus(orderNo);
        //支付成功
        if (result) {
            return ResultVO.ok().message("支付成功!");
        }
        //支付中
        return ResultVO.setResult(ResultCodeEnum.PAY_RUN);
    }
}

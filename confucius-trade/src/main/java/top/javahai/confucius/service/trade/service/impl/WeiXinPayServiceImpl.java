package top.javahai.confucius.service.trade.service.impl;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.helper.HttpClientHelper;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.trade.entity.Order;
import top.javahai.confucius.service.trade.service.OrderService;
import top.javahai.confucius.service.trade.service.WeiXinPayService;
import top.javahai.confucius.service.trade.util.WeiXinPayProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/28 - 18:05
 **/
@Service
@Slf4j
public class WeiXinPayServiceImpl implements WeiXinPayService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeiXinPayProperties weixinPayProperties;

    @Override
    public Map<String, Object> createNative(String orderNo, String remoteAddr) {
        try{
            //根据课程订单号获取订单
            Order order = orderService.getOrderByOrderNo(orderNo);
            //TODO 第一次支付后取消了支付，原本的订单号不能再用，微信会报订单号重复所以需要删除更新订单号
            //因为微信订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。所以这里出力是生成一个新的订单号并更新
            //TODO 数据库表建立多一个字段：out_trade_no(下单号)，每次调用该方法就重新生成一个



            //调用微信api接口：统一下单（支付订单）
            HttpClientHelper client = new HttpClientHelper("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //组装接口参数
            Map<String, String> params = new HashMap<>(16);
            //关联的公众号的appid
            params.put("appid", weixinPayProperties.getAppId());
            //商户号
            params.put("mch_id", weixinPayProperties.getPartner());
            //生成随机字符串
            params.put("nonce_str", WXPayUtil.generateNonceStr());
            params.put("body", order.getCourseTitle());
            params.put("out_trade_no", orderNo);

            //注意，这里必须使用字符串类型的参数（总金额：分）
            String totalFee = order.getTotalFee().intValue() + "";
            params.put("total_fee", totalFee);

            params.put("spbill_create_ip", remoteAddr);
            params.put("notify_url", weixinPayProperties.getNotifyUrl());
            params.put("trade_type", "NATIVE");

            //将参数转换成xml字符串格式：生成带有签名的xml格式字符串
            String xmlParams = WXPayUtil.generateSignedXml(params, weixinPayProperties.getPartnerKey());
            log.info("\n xmlParams：\n" + xmlParams);
            //将参数放入请求对象的方法体
            client.setXmlParam(xmlParams);
            //使用https形式发送
            client.setHttps(true);
            //发送Post请求
            client.post();
            //得到响应结果
            String resultXml = client.getContent();
            log.info("\n resultXml：\n" + resultXml);
            //将xml响应结果转成map对象
            Map<String, String> resultMap = WXPayUtil.xmlToMap(resultXml);

            //错误处理
            if("FAIL".equals(resultMap.get("return_code")) || "FAIL".equals(resultMap.get("result_code"))){
                log.error("微信支付统一下单错误 - "
                        + "return_code: " + resultMap.get("return_code")
                        + "return_msg: " + resultMap.get("return_msg")
                        + "result_code: " + resultMap.get("result_code")
                        + "err_code: " + resultMap.get("err_code")
                        + "err_code_des: " + resultMap.get("err_code_des"));

                throw new BaseException(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
            }

            //组装需要的内容
            Map<String, Object> map = new HashMap<>();
            //响应码
            map.put("result_code", resultMap.get("result_code"));
            //生成二维码的url
            map.put("code_url", resultMap.get("code_url"));
            //课程id
            map.put("course_id", order.getCourseId());
            //订单总金额
            map.put("total_fee", order.getTotalFee());
            //订单号
            map.put("out_trade_no", orderNo);

            return map;

        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.PAY_UNIFIEDORDER_ERROR);
        }
    }
}

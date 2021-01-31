package top.javahai.confucius.service.trade.service;

import java.util.Map;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/28 - 18:05
 **/
public interface WeiXinPayService {

    /**
     *Native场景下，调用微信平台生成付款二维码
     * @param orderNo
     * @param remoteAddr
     * @return
     */
    Map<String, Object> createNative(String orderNo, String remoteAddr);

}

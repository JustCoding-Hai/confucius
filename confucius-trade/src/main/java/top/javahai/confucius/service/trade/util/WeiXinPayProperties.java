package top.javahai.confucius.service.trade.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @program: confucius
 * @description: 微信支付属性配置
 * @create 2021/1/28 - 18:01
 **/
@Data
@Component
@ConfigurationProperties(prefix = "weixin.pay")
public class WeiXinPayProperties {
    private String appId;
    private String partner;
    private String partnerKey;
    private String notifyUrl;
}

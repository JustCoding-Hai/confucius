package top.javahai.confucius.service.uc.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @program: confucius
 * @description: 用户中心属性
 * @create 2021/1/26 - 16:38
 **/
@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class UserCenterProperties {

    private String appId;
    private String appSecret;
    private String redirectUri;
}

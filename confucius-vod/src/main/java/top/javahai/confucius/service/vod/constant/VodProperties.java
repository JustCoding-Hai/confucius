package top.javahai.confucius.service.vod.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/19 - 23:52
 **/
@Data
@Component
@ConfigurationProperties(prefix="aliyun.vod")
public class VodProperties {
    private String keyId;
    private String keySecret;
    private String templateGroupId;
    private String workflowId;
}

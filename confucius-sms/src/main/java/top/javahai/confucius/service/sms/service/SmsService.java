package top.javahai.confucius.service.sms.service;

import com.aliyuncs.exceptions.ClientException;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/24 - 11:31
 **/
public interface SmsService {
    /**
     * 处理验证码发送
     * @param mobile
     * @throws ClientException
     */
    void sendVerificationCode(String mobile) throws ClientException;
}

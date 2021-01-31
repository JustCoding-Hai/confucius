package top.javahai.confucius.service.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.helper.*;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.service.sms.contant.SmsContants;
import top.javahai.confucius.service.sms.service.SmsService;
import top.javahai.confucius.service.sms.util.SmsProperties;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/24 - 11:31
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SmsProperties smsProperties;

    @Override
    public void sendVerificationCode(String mobile) throws ClientException {
        //校验手机号是否合法
        if (StringHelper.isBlank(mobile)||!FormHelper.isMobile(mobile)){
            throw new BaseException(ResultCodeEnum.LOGIN_PHONE_ERROR);
        }
        //生成验证码
        //String verificationCode= RandomHelper.getFourBitRandom();

        //发送验证码
        //send(mobile,verificationCode);

        //TODO 测试使用
        String verificationCode="9527";

        //将验证码放入到Redis缓存,设置过期时间为5分钟
        redisTemplate.opsForValue().set(mobile,verificationCode,5, TimeUnit.MINUTES);

    }

    /**
     * 调用阿里云服务发送短信
     * @param mobile
     * @param verificationCode
     */
    private void send(String mobile,String verificationCode) throws ClientException {
        //创建client对象
        DefaultProfile profile = DefaultProfile.getProfile(
                smsProperties.getRegionId(),
                smsProperties.getKeyId(),
                smsProperties.getKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        //组装请求参数
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", smsProperties.getRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", smsProperties.getSignName());
        request.putQueryParameter("TemplateCode", smsProperties.getTemplateCode());
        HashMap<String, Object> templateParam = new HashMap<>(16);
        templateParam.put("code",verificationCode);
        //TemplateParam参数需要是Json格式
        request.putQueryParameter("TemplateParam",JsonHelper.toJSONString(templateParam));
        //获取返回结果
        CommonResponse response = client.getCommonResponse(request);
        String data = response.getData();
        HashMap<String,String> responseMap = JsonHelper.parseObject(data, HashMap.class);
        String code="";
        String message="";
        if (ObjectHelper.isNotEmpty(responseMap)){
            code=responseMap.get("Code");
            message=responseMap.get("Message");
        }
        if (SmsContants.CODE_BUSINESS_LIMIT_CONTROL.equals(code)) {
            log.error("短信发送过于频繁 " + "【code】" + code + ", 【message】" + message);
            throw new BaseException(ResultCodeEnum.SMS_SEND_ERROR_BUSINESS_LIMIT_CONTROL);
        }

        if (!SmsContants.CODE_OK.equals(code)) {
            log.error("短信发送失败 " + " - code: " + code + ", message: " + message);
            throw new BaseException(ResultCodeEnum.SMS_SEND_ERROR);
        }

    }
}

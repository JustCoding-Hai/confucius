package top.javahai.confucius.service.sms.controller;

import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.sms.service.SmsService;

/**
 * @author Hai
 * @program: confucius
 * @description: 短信接口服务
 * @create 2021/1/24 - 11:25
 **/
@RestController
@RequestMapping("/api/sms")
@Api(tags= "短信管理",value = "ApiSmsController")
@Slf4j
public class ApiSmsController {

    @Autowired
    SmsService smsService;

    @GetMapping("/send/{mobile}")
    public ResultVO getCode(@PathVariable String mobile) throws ClientException {
        //发送验证码
        smsService.sendVerificationCode(mobile);
        return ResultVO.ok("短信发送成功！");
    }
}

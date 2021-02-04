package top.javahai.confucius.service.admin.controller;

import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.base.service.model.LoginInfo;
import top.javahai.confucius.frame.common.result.ResultVO;

/**
 * @author Hai
 * @program: confucius-frame
 * @description: 登录接口控制器
 * @create 2021/1/9 - 17:30
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    public ResultVO<LoginInfo> login(){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setToken("admin");
        return ResultVO.ok().data(loginInfo);
    }

    @GetMapping("/info")
    public ResultVO<LoginInfo> getInfo(){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName("admin");
        loginInfo.setAvatar("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        loginInfo.setRoles("[admin]");
        return ResultVO.ok().data(loginInfo);
    }

    @PostMapping("/logout")
    public ResultVO logout(){
        return ResultVO.ok();
    }


}

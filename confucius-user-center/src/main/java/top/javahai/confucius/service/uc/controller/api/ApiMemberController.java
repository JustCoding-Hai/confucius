package top.javahai.confucius.service.uc.controller.api;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.entity.JwtInfo;
import top.javahai.confucius.frame.common.helper.JwtHelper;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.uc.entity.vo.LoginVO;
import top.javahai.confucius.service.uc.entity.vo.RegisterVO;
import top.javahai.confucius.service.uc.service.MemberService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hai
 * @program: confucius
 * @description: 前台用户中心控制器
 * @create 2021/1/25 - 11:45
 **/
@Api(tags = "会员管理",value = "ApiMemberController")
@RestController
@RequestMapping("/api/uc/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员注册")
    @PostMapping("/register")
    public ResultVO register(@RequestBody RegisterVO registerVO){
        memberService.register(registerVO);
        return ResultVO.ok("注册成功！");
    }

    @ApiOperation(value = "会员登录")
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody LoginVO loginVO){
        String token=memberService.login(loginVO);
        return new ResultVO<String>().success().data(token).message("登录成功！");
    }

    @ApiOperation(value = "获取登录信息")
    @GetMapping("/get-login-info")
    public ResultVO<JwtInfo> getLoginInfo(HttpServletRequest request){
        try{
            JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
            return new ResultVO<JwtInfo>().success().data(jwtInfo);
        }catch (Exception e){
            //TODO 提示用户会话超时，请重新登录
            log.error("解析用户信息失败，错误信息:{}"+ ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }

    }

}

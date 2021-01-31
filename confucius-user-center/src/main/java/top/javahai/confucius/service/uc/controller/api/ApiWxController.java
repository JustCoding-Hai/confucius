package top.javahai.confucius.service.uc.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.entity.JwtInfo;
import top.javahai.confucius.frame.common.helper.*;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.uc.entity.Member;
import top.javahai.confucius.service.uc.remote.WxClient;
import top.javahai.confucius.service.uc.service.MemberService;
import top.javahai.confucius.service.uc.util.UserCenterProperties;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UnknownFormatConversionException;

/**
 * @author Hai
 * @program: confucius
 * @description: 微信接口控制器
 * @create 2021/1/26 - 16:39
 **/
@Controller
@Slf4j
public class ApiWxController {
    @Autowired
    UserCenterProperties userCenterProperties;

    @Autowired
    WxClient wxClient;

    @Autowired
    MemberService memberService;

    /**
     * 生成二维码链接
     * @param session
     * @return
     */
    @GetMapping("/api/uc/wx/login")
    public String generateQrCodeConnect(HttpSession session){
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        //处理回调URI
        String redirectUri="";
        try {
            redirectUri= URLEncoder.encode(userCenterProperties.getRedirectUri(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.URL_ENCODE_ERROR);
        }

        //处理state:生成随机数，存入Session，用于后续的校验
        String state = UUIDHelper.getUuid();
        log.info("generate qrconnect state:{}",state);
        session.setAttribute("wx_open_state",state);

        //填充二维码链接的变量
        String qrCodeUrl = String.format(baseUrl, userCenterProperties.getAppId(), redirectUri, state);
        return "redirect:"+qrCodeUrl;
    }

    @GetMapping("/api/ucenter/wx/callback")
    public String callback(String code, String state,HttpSession session){
        //判断回调是否合法
        if (StringHelper.isAnyBlank(code,state)){
            log.error("Illegal callback for WeChat login");
            throw new BaseException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }
        String sessionState = (String) session.getAttribute("wx_open_state");
        if (!state.equals(sessionState)){
            log.error("Illegal callback for WeChat login");
            throw new BaseException(ResultCodeEnum.ILLEGAL_CALLBACK_REQUEST_ERROR);
        }
        //获取accessToken
        HashMap resultMap = wxClient.getAccessToken(code,state);

        //微信获取access_token响应成功
        String accessToken = (String)resultMap.get("access_token");
        String openid = (String)resultMap.get("openid");

        log.info("get access_token successfully,accessToken : {},openid:{}" ,accessToken,openid);

        //根据openid查询当前用户是否已经使用微信登录过该系统
        Member member = memberService.getByOpenid(openid);

        if(ObjectHelper.isEmpty(member)){
            //根据accessToken获取微信用户基本信息
            HashMap resultUserInfoMap = wxClient.getUserInfo(accessToken,openid);
            String nickname = (String)resultUserInfoMap.get("nickname");
            String headImgUrl = (String)resultUserInfoMap.get("headimgurl");
            Integer sex = (Integer)resultUserInfoMap.get("sex");
            //用户注册
            member = new Member();
            member.setOpenid(openid);
            member.setNickname(nickname);
            member.setAvatar(headImgUrl);
            member.setSex(sex.intValue());
            memberService.save(member);
        }
        //构造token返回
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtHelper.getJwtToken(jwtInfo, 1800);

        //携带token跳转
        return "redirect:http://localhost:3000?token=" + jwtToken;
    }
}

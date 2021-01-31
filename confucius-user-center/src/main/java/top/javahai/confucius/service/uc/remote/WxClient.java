package top.javahai.confucius.service.uc.remote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.helper.HttpClientHelper;
import top.javahai.confucius.frame.common.helper.JsonHelper;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.uc.util.UserCenterProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Hai
 * @program: confucius
 * @description: 微信接口调用
 * @create 2021/1/27 - 12:54
 **/
@Slf4j
@Component
public class WxClient {

    @Autowired
    UserCenterProperties userCenterProperties;

    /**
     * 根据携带授权票据code和appId以及appSecret请求access_token
     */
    public HashMap getAccessToken(String code,String state){
        //拼接url：https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        String accessTokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token";
        HashMap<String, String> accessTokenParam = new HashMap<>(16);
        accessTokenParam.put("appid",userCenterProperties.getAppId());
        accessTokenParam.put("secret",userCenterProperties.getAppSecret());
        accessTokenParam.put("code",code);
        accessTokenParam.put("grant_type","authorization_code");
        HttpClientHelper client = new HttpClientHelper(accessTokenUrl, accessTokenParam);

        String result;
        try {
            client.get();
            result = client.getContent();
        } catch (Exception e) {
            log.error("fail to get access_token,code={},state={}",code,state);
            throw new BaseException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }

        HashMap resultMap = JsonHelper.parseObject(result, HashMap.class);

        //判断微信获取access_token失败的响应
        Object errCodeObj = resultMap.get("errcode");
        if(errCodeObj != null){
            String errMsg = (String)resultMap.get("errmsg");
            Double errCode = (Double)errCodeObj;
            log.error("fail to get access_token, errcode: {},errmsg:{}",errCode,errMsg);
            throw new BaseException(ResultCodeEnum.FETCH_ACCESSTOKEN_FAILD);
        }
        return resultMap;
    }

    /**
     * 根据accessToken和openid获取微信用户基本信息
     * @param accessToken
     * @param openid
     * @return
     */
    public HashMap getUserInfo(String accessToken, String openid){
        //向微信的资源服务器发起请求，获取当前用户的用户信息
        String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
        Map<String, String> baseUserInfoParam = new HashMap(16);
        baseUserInfoParam.put("access_token", accessToken);
        baseUserInfoParam.put("openid", openid);
        HttpClientHelper client = new HttpClientHelper(baseUserInfoUrl, baseUserInfoParam);

        String resultUserInfo;
        try {
            client.get();
            resultUserInfo = client.getContent();
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }

        HashMap<String, Object> resultUserInfoMap = JsonHelper.parseObject(resultUserInfo, HashMap.class);
        if(resultUserInfoMap.get("errcode") != null){
            log.error("获取用户信息失败" + "，message：" + resultUserInfoMap.get("errmsg"));
            throw new BaseException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }

        return resultUserInfoMap;
    }
}

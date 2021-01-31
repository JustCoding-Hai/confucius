package top.javahai.confucius.service.uc.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 登录信息校验实体
 * @create 2021/1/25 - 18:50
 **/
@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;
}

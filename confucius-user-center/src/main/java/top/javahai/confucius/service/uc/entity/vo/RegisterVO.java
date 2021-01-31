package top.javahai.confucius.service.uc.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 用户注册对象
 * @create 2021/1/25 - 11:44
 **/
@Data
public class RegisterVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}

package top.javahai.confucius.frame.base.service.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Hai
 * @program: confucius
 * @description: 老师数据传输对象
 * @create 2021/1/27 - 22:39
 **/
@Data
public class MemberDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 会员id
     */
    private String id;
    /**
     *手机号
     */
    private String mobile;
    /**
     *昵称
     */
    private String nickname;
}

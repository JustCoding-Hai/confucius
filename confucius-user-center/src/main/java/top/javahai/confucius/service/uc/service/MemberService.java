package top.javahai.confucius.service.uc.service;

import top.javahai.confucius.frame.base.service.dto.MemberDTO;
import top.javahai.confucius.service.uc.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.uc.entity.vo.LoginVO;
import top.javahai.confucius.service.uc.entity.vo.RegisterVO;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-24
 */
public interface MemberService extends IService<Member> {

    /**
     * 用户注册
     * @param registerVO
     */
    void register(RegisterVO registerVO);

    /**
     * 用户登录校验
     * @param loginVO
     * @return
     */
    String login(LoginVO loginVO);

    /**
     * 根据微信的openid获取用户数据
     * @param openid
     * @return
     */
    Member getByOpenid(String openid);

    /**
     * 根据id获取会员信息
     * @param memberId
     * @return
     */
    MemberDTO getMemberDTOByMemberId(String memberId);

    /**
     * 统计用户注册数
     * @param day
     * @return
     */
    Integer countRegisterNumByDay(String day);
}

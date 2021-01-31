package top.javahai.confucius.service.uc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.javahai.confucius.frame.base.service.dto.MemberDTO;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.entity.JwtInfo;
import top.javahai.confucius.frame.common.helper.*;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.service.uc.entity.Member;
import top.javahai.confucius.service.uc.entity.vo.LoginVO;
import top.javahai.confucius.service.uc.entity.vo.RegisterVO;
import top.javahai.confucius.service.uc.mapper.MemberMapper;
import top.javahai.confucius.service.uc.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-24
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void register(RegisterVO registerVO) {
        String nickname = registerVO.getNickname();
        String mobile = registerVO.getMobile();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();

        //校验参数
        if (StringHelper.isEmpty(mobile)
                || !FormHelper.isMobile(mobile)
                || StringHelper.isEmpty(password)
                || StringHelper.isEmpty(code)
                || StringHelper.isEmpty(nickname)) {
            throw new BaseException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验验证码
        String checkCode = (String)redisTemplate.opsForValue().get(mobile);
        if(!code.equals(checkCode)){
            throw new BaseException(ResultCodeEnum.CODE_ERROR);
        }

        //是否被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count>0){
            throw new BaseException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        //注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(SecurityHelper.MD5Encrypt(password));
        member.setDisabled(false);
        member.setAvatar("https://guli-file-helen.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
        baseMapper.insert(member);
    }


    @Override
    public String login(LoginVO loginVO) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        log.info("member try to login at {},mobile:{},password:{}", new Date(),mobile,password);

        //校验参数
        if (StringHelper.isEmpty(mobile)
                || !FormHelper.isMobile(mobile)
                || StringHelper.isEmpty(password)) {
            throw new BaseException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验手机号
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new BaseException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        //检验用户是否被禁用
        if(member.getDisabled()){
            throw new BaseException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //校验密码
        if(!SecurityHelper.MD5Encrypt(password).equals(member.getPassword())){
            throw new BaseException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        //三分钟过期
        return JwtHelper.getJwtToken(jwtInfo, 1800);
    }

    @Override
    public Member getByOpenid(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid",openid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MemberDTO getMemberDTOByMemberId(String memberId) {
        Member member = baseMapper.selectById(memberId);
        MemberDTO memberDTO = new MemberDTO();
        BeanHelper.copyProperties(member,memberDTO);
        return memberDTO;
    }

    @Override
    public Integer countRegisterNumByDay(String day) {
        return baseMapper.countRegisterNumByDay(day);
    }
}

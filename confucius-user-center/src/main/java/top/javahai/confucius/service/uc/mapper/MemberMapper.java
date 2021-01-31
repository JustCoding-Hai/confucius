package top.javahai.confucius.service.uc.mapper;

import org.apache.ibatis.annotations.Param;
import top.javahai.confucius.service.uc.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2021-01-24
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * count Register Number By Day
     * @param day
     * @return
     */
    Integer countRegisterNumByDay(@Param("day") String day);
}

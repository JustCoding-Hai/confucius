package top.javahai.confucius.service.portal.mapper;

import top.javahai.confucius.service.portal.entity.CourseCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.javahai.confucius.service.portal.entity.vo.CourseCollectVO;

import java.util.List;

/**
 * <p>
 * 课程收藏 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {

    /**
     * （分页）查询课程收藏
     * @param memberId
     * @return
     */
    List<CourseCollectVO> selectPageByMemberId(String memberId);
}

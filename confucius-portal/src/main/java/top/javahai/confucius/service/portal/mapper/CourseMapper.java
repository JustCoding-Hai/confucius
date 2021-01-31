package top.javahai.confucius.service.portal.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import top.javahai.confucius.frame.base.service.dto.CourseDTO;
import top.javahai.confucius.service.portal.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.javahai.confucius.service.portal.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseVO;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据id获取课程和对应的讲师信息
     * @param courseId
     * @return
     */
    WebCourseVO selectWebCourseVOById(@Param("courseId") String courseId);

    /**
     * 分页查询实现
     * 这里使用@Param(Constants.WRAPPER)和xml文件中的${ew.customSqlSegment}对应，
     * mq将sql拼接加上查询条件
     * @param queryWrapper mp自动组装queryWrapper
     * @return
     */
    List<CourseInfoVO> selectCourseInfoVO(@Param(Constants.WRAPPER) QueryWrapper<CourseInfoVO> queryWrapper);

    /**
     * 查询CourseDTO数据
     * @param courseId
     * @return
     */
    CourseDTO selectCourseDTOById(@Param("id") String courseId);
}

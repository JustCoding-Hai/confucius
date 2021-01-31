package top.javahai.confucius.service.education.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.javahai.confucius.service.education.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.javahai.confucius.service.education.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.education.entity.vo.CoursePublishInfoVO;
import top.javahai.confucius.service.education.entity.vo.WebCourseVO;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 分页查询实现
     * 这里使用@Param(Constants.WRAPPER)和xml文件中的${ew.customSqlSegment}对应，
     * mq将sql拼接加上查询条件
     * @param pageParam   mp自动组装分页参数
     * @param queryWrapper mp自动组装queryWrapper
     * @return
     */
    List<CourseInfoVO> selectPageByCourseQueryVO(
            Page<CourseInfoVO> pageParam,
            @Param(Constants.WRAPPER) QueryWrapper<CourseInfoVO> queryWrapper);

    /**
     * 根据课程id获取课程发布信息
     * @param id
     * @return
     */
    CoursePublishInfoVO getCoursePublishVOById(@Param("id") String id);

    /**
     * 根据id获取课程和对应的讲师信息
     * @param courseId
     * @return
     */
    WebCourseVO selectWebCourseVOById(@Param("courseId") String courseId);
}

package top.javahai.confucius.service.portal.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.frame.base.service.dto.CourseDTO;
import top.javahai.confucius.service.portal.entity.Course;
import top.javahai.confucius.service.portal.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseQueryVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface CourseService extends IService<Course> {


    /**
     * 查询course信息
     * @param queryWrapper
     * @return
     */
    List<CourseInfoVO> selectCourseInfoVO(QueryWrapper<CourseInfoVO> queryWrapper);

    /**
     * 前台搜索课程
     * @param webCourseQueryVo
     * @return
     */
    List<Course> webSelectCourseInfoList(WebCourseQueryVO webCourseQueryVo);

    /**
     * 课程信息显示
     * @param courseId
     * @return
     */
    WebCourseVO selectWebCourseVoById(String courseId);

    /**
     * 获取首页最热门前8条课程数据
     * @return
     */
    List<Course> selectHotCourse();

    /**
     * 根据id获取课程DTO
     * @param courseId
     * @return
     */
    CourseDTO getCourseDTOById(String courseId);

    /**
     * 更新课程销量
     * @param id
     */
    void updateBuyCountById(String id);
}

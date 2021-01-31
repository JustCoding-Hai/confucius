package top.javahai.confucius.service.education.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.javahai.confucius.service.education.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.education.entity.vo.*;

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
     * 保存课程信息和课程详情信息
     * @param courseFormVO
     * @return
     */
    String saveCourseInfo(CourseFormVO courseFormVO);

    /**
     * 根据id查询课程详细信息
     * @param id
     * @return
     */
    CourseFormVO getCourseInfoById(String id);

    /**
     * 根据id更新课程信息
     * @param courseFormVO
     */
    void updateCourseInfoById(CourseFormVO courseFormVO);

    /**
     * 分页查询
     * @param pageParam
     * @param courseQueryVO
     * @return
     */
    Page<CourseInfoVO> selectPage(Page<CourseInfoVO> pageParam, CourseQueryVO courseQueryVO);

    /**
     * 根据id删除课程及其所有的关联数据
     * @param id
     * @return
     */
    boolean deleteCourseById(String id);

    /**
     * 根据id删除课程封面
     * @param id
     * @return
     */
    void deleteCoverById(String id);

    /**
     * 根据id获取课程的发布信息
     * @param id
     * @return
     */
    CoursePublishInfoVO getCoursePublishVOById(String id);

    /**
     * 根据课程id发布课程
     * @param id
     * @return
     */
    boolean publishCourseById(String id);

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
}

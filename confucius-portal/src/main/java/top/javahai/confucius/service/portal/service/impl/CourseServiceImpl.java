package top.javahai.confucius.service.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import top.javahai.confucius.frame.base.service.dto.CourseDTO;
import top.javahai.confucius.frame.common.helper.StringHelper;
import top.javahai.confucius.service.portal.constant.CourseConstants;
import top.javahai.confucius.service.portal.entity.Course;
import top.javahai.confucius.service.portal.entity.vo.ChapterVO;
import top.javahai.confucius.service.portal.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseQueryVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseVO;
import top.javahai.confucius.service.portal.mapper.CourseMapper;
import top.javahai.confucius.service.portal.service.ChapterService;
import top.javahai.confucius.service.portal.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private ChapterService chapterService;

    @Override
    public List<CourseInfoVO> selectCourseInfoVO(QueryWrapper<CourseInfoVO> queryWrapper) {
        return baseMapper.selectCourseInfoVO(queryWrapper);
    }

    @Override
    public List<Course> webSelectCourseInfoList(WebCourseQueryVO webCourseQueryVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        //查询已发布的课程
        queryWrapper.eq("status", CourseConstants.COURSE_STATUS_PUBLISHED);

        if (!StringHelper.isEmpty(webCourseQueryVo.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", webCourseQueryVo.getSubjectParentId());
        }

        if (!StringHelper.isEmpty(webCourseQueryVo.getSubjectId())) {
            queryWrapper.eq("subject_id", webCourseQueryVo.getSubjectId());
        }

        if (!StringHelper.isEmpty(webCourseQueryVo.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }

        if (!StringHelper.isEmpty(webCourseQueryVo.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringHelper.isEmpty(webCourseQueryVo.getPriceSort())) {
            if (webCourseQueryVo.getType()==null||webCourseQueryVo.getType()==1){
                queryWrapper.orderByAsc("price");
            }else {
                queryWrapper.orderByDesc("price");
            }
        }

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public WebCourseVO selectWebCourseVoById(String courseId) {
        //更新课程的浏览量
        Course course = baseMapper.selectById(courseId);
        course.setViewCount(course.getViewCount()+1);
        baseMapper.updateById(course);
        //获取课程信息
        WebCourseVO webCourseVO=baseMapper.selectWebCourseVOById(courseId);
        //获取课程的章节、课时信息
        List<ChapterVO> chapterVOList = chapterService.getNestedList(courseId);
        webCourseVO.setChapterVOList(chapterVOList);
        return webCourseVO;
    }

    @Cacheable(value = "index",key = "'selectHotCourse'")
    @Override
    public List<Course> selectHotCourse() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public CourseDTO getCourseDTOById(String courseId) {

        return baseMapper.selectCourseDTOById(courseId);
    }

    @Override
    public void updateBuyCountById(String id) {
        Course course = baseMapper.selectById(id);
        course.setBuyCount(course.getBuyCount()+1);
        this.updateById(course);
    }
}

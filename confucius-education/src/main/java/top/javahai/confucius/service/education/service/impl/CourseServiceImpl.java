package top.javahai.confucius.service.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import top.javahai.confucius.frame.common.helper.BeanHelper;
import top.javahai.confucius.frame.common.helper.ObjectHelper;
import top.javahai.confucius.frame.common.helper.StringHelper;
import top.javahai.confucius.service.education.constant.CourseConstants;
import top.javahai.confucius.service.education.entity.*;
import top.javahai.confucius.service.education.entity.vo.*;
import top.javahai.confucius.service.education.mapper.ChapterMapper;
import top.javahai.confucius.service.education.mapper.CourseMapper;
import top.javahai.confucius.service.education.remote.OssClient;
import top.javahai.confucius.service.education.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    CourseDescriptionService courseDescriptionService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CourseCollectService courseCollectService;

    @Autowired
    OssClient ossClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveCourseInfo(CourseFormVO courseFormVO) {

        //保存课程基本信息
        Course course = new Course();
        BeanHelper.copyProperties(courseFormVO,course);
        course.setStatus(CourseConstants.COURSE_STATUS_DRAFT);
        baseMapper.insert(course);

        //保存课程描述信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseFormVO.getDescription());
        courseDescriptionService.save(courseDescription);
        return course.getId();
    }

    @Override
    public CourseFormVO getCourseInfoById(String id) {
        Course course = baseMapper.selectById(id);
        if (ObjectHelper.isEmpty(course)) {
            return null;
        }
        CourseFormVO courseFormVO = new CourseFormVO();
        BeanHelper.copyProperties(course, courseFormVO);
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        courseFormVO.setDescription(courseDescription.getDescription());
        return courseFormVO;
    }

    @Override
    public void updateCourseInfoById(CourseFormVO courseFormVO) {
        Course course = new Course();
        BeanHelper.copyProperties(courseFormVO,course);
        updateById(course);
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseFormVO.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public Page<CourseInfoVO> selectPage(Page<CourseInfoVO> pageParam, CourseQueryVO courseQueryVO) {
        QueryWrapper<CourseInfoVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");

        String title = courseQueryVO.getTitle();
        String teacherId = courseQueryVO.getTeacherId();
        String subjectParentId = courseQueryVO.getSubjectParentId();
        String subjectId = courseQueryVO.getSubjectId();

        if (!StringHelper.isEmpty(title)) {
            queryWrapper.like("c.title", title);
        }

        if (!StringHelper.isEmpty(teacherId) ) {
            queryWrapper.eq("c.teacher_id", teacherId);
        }

        if (!StringHelper.isEmpty(subjectParentId)) {
            queryWrapper.eq("c.subject_parent_id", subjectParentId);
        }

        if (!StringHelper.isEmpty(subjectId)) {
            queryWrapper.eq("c.subject_id", subjectId);
        }

        List<CourseInfoVO> records=baseMapper.selectPageByCourseQueryVO(pageParam,queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCourseById(String id) {

        //收藏数据
        QueryWrapper<CourseCollect> courseCollectQueryWrapper = new QueryWrapper<>();
        courseCollectQueryWrapper.eq("course_id",id);
        courseCollectService.remove(courseCollectQueryWrapper);
        //评论数据
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("course_id",id);
        commentService.remove(commentQueryWrapper);

        //课程视频
        videoService.removeMediaVideoByCourseId(id);

        //课时数据
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", id);
        videoService.remove(videoQueryWrapper);

        //章节数据
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        chapterService.remove(chapterQueryWrapper);

        //课程详情
        courseDescriptionService.removeById(id);



        //课程封面
        deleteCoverById(id);
        //课程数据
        return this.removeById(id);

    }

    @Override
    public void deleteCoverById(String id) {
        Course course = getById(id);
        String cover = course.getCover();
        if (ObjectHelper.isNotEmpty(course)&&StringHelper.isNotBlank(cover)){
            ossClient.removeFile(cover);
        }
    }

    @Override
    public CoursePublishInfoVO getCoursePublishVOById(String id) {
        return baseMapper.getCoursePublishVOById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(CourseConstants.COURSE_STATUS_PUBLISHED);
        return this.updateById(course);
    }

    @Override
    public List<CourseInfoVO> selectCourseInfoVO(QueryWrapper<CourseInfoVO> queryWrapper) {
        return baseMapper.selectPageByCourseQueryVO(null,queryWrapper);
    }


}

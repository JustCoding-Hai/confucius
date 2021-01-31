package top.javahai.confucius.service.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import top.javahai.confucius.service.portal.entity.Teacher;
import top.javahai.confucius.service.portal.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.portal.entity.vo.WebTeacherInfoVO;
import top.javahai.confucius.service.portal.mapper.TeacherMapper;
import top.javahai.confucius.service.portal.service.CourseService;
import top.javahai.confucius.service.portal.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    CourseService courseService;

    @Override
    public WebTeacherInfoVO getTeacherInfoById(String id) {
        Teacher teacher = getById(id);
        QueryWrapper<CourseInfoVO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("t.id",id);
        List<CourseInfoVO> courseInfoVOList = courseService.selectCourseInfoVO(queryWrapper);
        WebTeacherInfoVO webTeacherInfoVO = new WebTeacherInfoVO();
        webTeacherInfoVO.setTeacher(teacher);
        webTeacherInfoVO.setCourseInfoVOList(courseInfoVOList);
        return webTeacherInfoVO;
    }

    @Cacheable(value = "index",key = "'selectHotTeacher'")
    @Override
    public List<Teacher> selectHotTeacher() {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        queryWrapper.last("limit 4");
        return baseMapper.selectList(queryWrapper);
    }
}

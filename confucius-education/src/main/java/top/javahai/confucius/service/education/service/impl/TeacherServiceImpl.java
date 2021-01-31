package top.javahai.confucius.service.education.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.confucius.frame.common.helper.ObjectHelper;
import top.javahai.confucius.frame.common.helper.StringHelper;
import top.javahai.confucius.service.education.entity.Teacher;
import top.javahai.confucius.service.education.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.education.entity.vo.WebTeacherInfoVO;
import top.javahai.confucius.service.education.entity.vo.TeacherQueryVO;
import top.javahai.confucius.service.education.mapper.TeacherMapper;
import top.javahai.confucius.service.education.remote.OssClient;
import top.javahai.confucius.service.education.service.CourseService;
import top.javahai.confucius.service.education.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    OssClient ossClient;

    @Autowired
    CourseService courseService;

    @Override
    public Page<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVO teacherQueryVO) {
        if (ObjectHelper.isEmpty(teacherQueryVO)){
            return page(pageParam);
        }
        String name = teacherQueryVO.getName();
        Integer level = teacherQueryVO.getLevel();
        String joinDateBegin = teacherQueryVO.getJoinDateBegin();
        String joinDateEnd = teacherQueryVO.getJoinDateEnd();
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if (StringHelper.isNotEmpty(name)){
            queryWrapper.likeRight("name",name);
        }
        if (!ObjectHelper.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if (StringHelper.isNotEmpty(joinDateBegin)){
            queryWrapper.ge("join_date",joinDateBegin);
        }
        if (StringHelper.isNotEmpty(joinDateEnd)){
            queryWrapper.le("join_date",joinDateEnd);
        }

        return baseMapper.selectPage(pageParam,queryWrapper);
    }

    @Override
    public List<Map<String, Object>> getNameListByKeyword(String keyword) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name").likeRight("name",keyword);

        List<Map<String, Object>> mapList = baseMapper.selectMaps(queryWrapper);

        return mapList;
    }

    @Override
    public void removeAvatarByUrl(String avatar) {
        if(StringUtils.isNotBlank(avatar)){
            //TODO 业务性质无关联，所以应该用MQ处理
          ossClient.removeFile(avatar);

        }
    }

    @Override
    public String getTeacherAvatarById(String id) {
        Teacher teacher = baseMapper.selectById(id);
        if (teacher!=null){
            return teacher.getAvatar();
        }
        return null;
    }

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
}

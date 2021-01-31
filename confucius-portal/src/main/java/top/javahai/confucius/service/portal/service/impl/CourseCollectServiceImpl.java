package top.javahai.confucius.service.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.javahai.confucius.service.portal.entity.CourseCollect;
import top.javahai.confucius.service.portal.entity.vo.CourseCollectVO;
import top.javahai.confucius.service.portal.mapper.CourseCollectMapper;
import top.javahai.confucius.service.portal.service.CourseCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Service
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements CourseCollectService {

    @Override
    public Boolean isCollect(String courseId, String id) {
        QueryWrapper<CourseCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",id);
        return baseMapper.selectCount(queryWrapper)>0;
    }

    @Override
    public void saveCourseCollect(String courseId, String memberId) {
        if (this.isCollect(courseId,memberId)){
            return;
        }
        CourseCollect courseCollect = new CourseCollect();
        courseCollect.setCourseId(courseId);
        courseCollect.setMemberId(memberId);
        this.save(courseCollect);
    }

    @Override
    public List<CourseCollectVO> selectListByMemberId(String memberId) {
        return baseMapper.selectPageByMemberId(memberId);
    }

    @Override
    public Boolean removeCourseCollect(String courseId, String memberId) {
        if (!this.isCollect(courseId,memberId)){
            return false;
        }
        QueryWrapper<CourseCollect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        queryWrapper.eq("member_id",memberId);
        return this.remove(queryWrapper);
    }
}

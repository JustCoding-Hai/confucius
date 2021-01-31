package top.javahai.confucius.service.portal.service;

import top.javahai.confucius.service.portal.entity.CourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.portal.entity.vo.CourseCollectVO;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
public interface CourseCollectService extends IService<CourseCollect> {

    /**
     * 课程是否被用户收藏
     * @param courseId
     * @param memberId
     * @return
     */
    Boolean isCollect(String courseId, String memberId);

    /**
     * 收藏课程
     * @param courseId
     * @param memberId
     */
    void saveCourseCollect(String courseId, String memberId);

    /**
     * 根据memberId获取课程收藏
     * @param memberId
     * @return
     */
    List<CourseCollectVO> selectListByMemberId(String memberId);

    /**
     * 取消课程收藏
     * @param courseId
     * @param memberId
     * @return
     */
    Boolean removeCourseCollect(String courseId, String memberId);
}

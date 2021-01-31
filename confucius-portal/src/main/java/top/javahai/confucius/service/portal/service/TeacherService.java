package top.javahai.confucius.service.portal.service;

import top.javahai.confucius.service.portal.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.portal.entity.vo.WebTeacherInfoVO;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 获取讲师的详细信息，包括其教的课程
     * @param id
     * @return
     */
    WebTeacherInfoVO getTeacherInfoById(String id);

    /**
     * 获取首页推荐前4条讲师数据
     * @return
     */
    List<Teacher> selectHotTeacher();
}

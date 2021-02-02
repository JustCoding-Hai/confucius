package top.javahai.confucius.service.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.javahai.confucius.service.admin.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.admin.entity.vo.TeacherQueryVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 分页查询
     * @param pageParam
     * @param teacherQueryVO
     * @return
     */
    Page<Teacher> selectPage(Page<Teacher> pageParam, TeacherQueryVO teacherQueryVO);

    /**
     * 通过关键词模糊查询教师名
     * @param keyword
     * @return
     */
    List<Map<String, Object>> getNameListByKeyword(String keyword);

    /**
     * 根据头像url执行删除
     * @param avatar
     */
    void removeAvatarByUrl(String avatar);

    /**
     * get Teacher By Id
     * @param id
     * @return
     */
    String getTeacherAvatarById(String id);
}

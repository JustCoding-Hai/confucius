package top.javahai.confucius.service.admin.service;

import top.javahai.confucius.service.admin.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.admin.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 删除章节数据及章节相关联数据
     * @param id
     * @return
     */
    boolean removeChapterById(String id);

    /**
     * 获取嵌套了视频数据的章节数据
     * @param courseId
     * @return
     */
    List<ChapterVO> getNestedList(String courseId);
}

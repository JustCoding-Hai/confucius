package top.javahai.confucius.service.portal.service;

import top.javahai.confucius.service.portal.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import top.javahai.confucius.service.portal.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 获取嵌套了视频数据的章节数据
     * @param courseId
     * @return
     */
    List<ChapterVO> getNestedList(String courseId);
}

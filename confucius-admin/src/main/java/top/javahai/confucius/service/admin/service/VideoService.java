package top.javahai.confucius.service.admin.service;

import top.javahai.confucius.service.admin.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface VideoService extends IService<Video> {

    /**
     * 根据课时id删除课时数据及其视频数据
     * @param id
     * @return
     */
    boolean removeVideoById(String id);

    /**
     * 根据章节id获取videoIds，删除多个视频
     * @return
     * @param chapterId
     */
    Boolean removeMediaVideoByChapterId(String chapterId);

    /**
     * 根据课程id获取videoIds，删除多个视频
     * @return
     * @param courseId
     */
    Boolean removeMediaVideoByCourseId(String courseId);
}

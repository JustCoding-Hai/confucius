package top.javahai.confucius.service.admin.mapper;

import org.apache.ibatis.annotations.Param;
import top.javahai.confucius.service.admin.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 根据Chapter Id获取章节下的所有视频id
     * @param chapterId
     * @return
     */
    List<String> getVideoIdsByChapterId(@Param("chapterId") String chapterId);

    /**
     * 根据Course Id获取课程下的所有视频id
     * @param courseId
     * @return
     */
    List<String> getVideoIdsByCourseId(@Param("courseId") String courseId);
}

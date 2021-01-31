package top.javahai.confucius.service.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.confucius.frame.common.helper.BeanHelper;
import top.javahai.confucius.frame.common.helper.CollectionHelper;
import top.javahai.confucius.service.portal.entity.Chapter;
import top.javahai.confucius.service.portal.entity.Video;
import top.javahai.confucius.service.portal.entity.vo.ChapterVO;
import top.javahai.confucius.service.portal.entity.vo.VideoVO;
import top.javahai.confucius.service.portal.mapper.ChapterMapper;
import top.javahai.confucius.service.portal.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.javahai.confucius.service.portal.service.VideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    VideoService videoService;

    @Override
    public List<ChapterVO> getNestedList(String courseId) {

        List<ChapterVO> chapterVOList = CollectionHelper.createArrayList();

        //获取章节信息
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseId);
        chapterQueryWrapper.orderByAsc("sort","id");
        List<Chapter> chapterList = baseMapper.selectList(chapterQueryWrapper);

        //获取视频信息
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseId);
        videoQueryWrapper.orderByAsc("sort","id");
        List<Video> videoList = videoService.getBaseMapper().selectList(videoQueryWrapper);

        //填充数据
        chapterList.forEach(chapter -> {
            ChapterVO chapterVO = new ChapterVO();
            BeanHelper.copyProperties(chapter,chapterVO);
            //设置关联的视频数据
            ArrayList<VideoVO> videoVOList = new ArrayList<>();
            videoList.forEach(video -> {
                if (video.getChapterId().equals(chapterVO.getId())){
                    VideoVO videoVO = new VideoVO();
                    BeanHelper.copyProperties(video,videoVO);
                    videoVOList.add(videoVO);
                }
            });
            chapterVO.setChildren(videoVOList);
            chapterVOList.add(chapterVO);
        });
        return chapterVOList;
    }
}

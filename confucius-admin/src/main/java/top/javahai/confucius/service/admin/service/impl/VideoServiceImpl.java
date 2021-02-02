package top.javahai.confucius.service.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.javahai.confucius.frame.common.helper.StringHelper;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.admin.entity.Video;
import top.javahai.confucius.service.admin.mapper.VideoMapper;
import top.javahai.confucius.service.admin.remote.VodClient;
import top.javahai.confucius.service.admin.service.VideoService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Service
@Slf4j
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    VodClient vodClient;

    @Override
    public boolean removeVideoById(String id) {
        //获取课时相关联的视频id
        Video video = getById(id);
        Boolean isDeleteVideo=false;
        String videoSourceId = video.getVideoSourceId();
        if (StringHelper.isNotBlank(videoSourceId)){
            ResultVO resultVO = vodClient.removeVideo(videoSourceId);
            log.info("remote call vod service to removeVideo,videoSourceId:{}",videoSourceId);
            isDeleteVideo=resultVO.getSuccess();
        }
        //两个都删除成功
        return isDeleteVideo&&removeById(id);
    }

    @Override
    public Boolean removeMediaVideoByChapterId(String chapterId) {
        List<String> videoIds=baseMapper.getVideoIdsByChapterId(chapterId);
        ResultVO resultVO = vodClient.removeVideoByIdList(videoIds);
        return resultVO.getSuccess();
    }

    @Override
    public Boolean removeMediaVideoByCourseId(String courseId) {
        List<String> videoIds=baseMapper.getVideoIdsByCourseId(courseId);
        ResultVO resultVO = vodClient.removeVideoByIdList(videoIds);
        return resultVO.getSuccess();
    }

}

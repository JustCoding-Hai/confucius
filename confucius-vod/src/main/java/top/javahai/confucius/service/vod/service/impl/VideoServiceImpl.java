package top.javahai.confucius.service.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.helper.CollectionHelper;
import top.javahai.confucius.frame.common.helper.StringHelper;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.vod.constant.VodProperties;
import top.javahai.confucius.service.vod.service.VideoService;
import top.javahai.confucius.service.vod.util.AliyunVodSDKUtils;

import javax.swing.border.TitledBorder;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/20 - 10:17
 **/
@Service
@Slf4j
public class VideoServiceImpl implements VideoService {


    @Autowired
    VodProperties vodProperties;

    @Override
    public String uploadVideo(MultipartFile file, String originalFileName) {
        String title = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String videoId;
        try {
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(vodProperties.getKeyId(), vodProperties.getKeySecret(), title, originalFileName, inputStream);
            //设置转码模板-压缩视频为流畅
            request.setTemplateGroupId("548992b12b9a065c0e19bab8658f8cf3");
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            videoId = response.getVideoId();
            if (StringHelper.isBlank(videoId)){
                log.error("阿里云视频上传失败：code:{},message:{}",response.getCode(),response.getMessage());
                throw new BaseException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
            }
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.VIDEO_UPLOAD_TOMCAT_ERROR);
        }
        return videoId;
    }

    @Override
    public void removeVideo(String videoId) throws ClientException {
        if (StringHelper.isBlank(videoId)){
            return;
        }
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());
        DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
        deleteVideoRequest.setVideoIds(videoId);
        log.info("阿里云视频执行删除：videoId:{}",videoId);
        client.getAcsResponse(deleteVideoRequest);
    }

    @Override
    public void removeVideoByIdList(List<String> idList) throws ClientException {
        if (CollectionHelper.isEmpty(idList)){
            return;
        }
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());
        DeleteVideoRequest deleteVideoRequest = new DeleteVideoRequest();
        int size=idList.size();
        StringBuffer videoIds = new StringBuffer();

        for (int i = 0; i < size; i++) {
            videoIds.append(idList.get(i));
            //如果videoIds中的id数量达到20或者拼接到最后一个就调用阿里云vod执行删除。(vod规定最多删除20个)
            if (i==size-1||i%20>=19){
                String videoIdsString = videoIds.toString();
                deleteVideoRequest.setVideoIds(videoIdsString);
                log.info("阿里云视频执行删除,videoIds:{}",videoIdsString);
                client.getAcsResponse(deleteVideoRequest);
                videoIds=new StringBuffer();
            }else{
                videoIds.append(",");
            }
        }

    }

    @Override
    public String getPlayAuth(String videoSourceId) throws ClientException {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(vodProperties.getKeyId(), vodProperties.getKeySecret());
        //创建请求对象
        GetVideoPlayAuthRequest authRequest = new GetVideoPlayAuthRequest();
        authRequest.setVideoId(videoSourceId);
        //获取响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(authRequest);
        //
        log.info("videoSourceId:{},getPlayAuth:{}",videoSourceId,response.getPlayAuth());
        return response.getPlayAuth();
    }
}

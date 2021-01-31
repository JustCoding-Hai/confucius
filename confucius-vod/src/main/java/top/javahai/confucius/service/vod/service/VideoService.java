package top.javahai.confucius.service.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/20 - 10:14
 **/
public interface VideoService {

    /**
     * 视频文件上传
     * @param file
     * @param originalFileName
     * @return
     */
    String uploadVideo(MultipartFile file,String originalFileName);

    /**
     * 通过videoId删除远程视频数据
     * @param videoId
     * @throws ClientException
     */
    void removeVideo(String videoId) throws ClientException;


    /**
     * 通过idList批量删除video
     * @param idList
     * @throws ClientException
     */
    void removeVideoByIdList(List<String> idList) throws ClientException;

    /**
     * 根据videoSourceId获取播放凭证
     * @param videoSourceId
     * @return
     */
    String getPlayAuth(String videoSourceId) throws ClientException;
}

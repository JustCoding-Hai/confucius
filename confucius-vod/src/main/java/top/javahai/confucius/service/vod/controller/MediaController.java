package top.javahai.confucius.service.vod.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.vod.service.VideoService;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/20 - 10:28
 **/
@Api(tags="阿里云视频点播")
@RestController
@RequestMapping("/admin/vod/media")
@Slf4j
public class MediaController {

    @Autowired
    VideoService videoService;

    @PostMapping("/upload")
    public ResultVO<String> uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String videoId = videoService.uploadVideo(file, file.getOriginalFilename());
        return new ResultVO<String>().success().message("上传成功").data(videoId);
    }

    @DeleteMapping
    public ResultVO removeVideo(
            @ApiParam(value="阿里云视频id", required = true)
            @RequestParam(value = "vodId") String vodId){

        try {
            videoService.removeVideo(vodId);
            return ResultVO.ok().message("视频删除成功!");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

}

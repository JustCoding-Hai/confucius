package top.javahai.confucius.service.vod.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.vod.service.VideoService;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/22 - 17:27
 **/

@Api(tags="阿里云视频点播")
@RestController
@RequestMapping("/api/vod/media")
@Slf4j
public class ApiMediaController {

    @Autowired
    VideoService videoService;

    @GetMapping("/play-auth/{videoSourceId}")
    public ResultVO<String> getPlayAuth(
            @ApiParam(value = "阿里云视频文件的id", required = true)
            @PathVariable String videoSourceId){

        try{
            String playAuth = videoService.getPlayAuth(videoSourceId);
            return  new ResultVO<String>().success().data(playAuth);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.FETCH_PLAYAUTH_ERROR);
        }
    }


}

package top.javahai.confucius.service.vod.remote;

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

import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 远程调用视频控制器
 * @create 2021/1/20 - 13:07
 **/
@Api(tags="阿里云视频点播远程调用接口")
@RestController
@RequestMapping("/admin/rpc/vod/media")
@Slf4j
public class MediaRpcController {

    @Autowired
    VideoService videoService;

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

    @DeleteMapping("/batch-remove")
    public ResultVO removeVideoByIdList(
            @ApiParam(value="阿里云视频id列表", required = true)
            @RequestBody List<String> idList){

        try {
            videoService.removeVideoByIdList(idList);
            return ResultVO.ok().message("视频删除成功!");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

}

package top.javahai.confucius.service.education.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.education.entity.Video;
import top.javahai.confucius.service.education.service.VideoService;

/**
 * @author Hai
 * @program: confucius
 * @description: 课时控制器
 * @create 2021/1/19 - 9:00
 **/
@Api(tags = "课时管理")
@RestController
@RequestMapping("/admin/edu/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课时")
    @PostMapping
    public ResultVO save(
            @ApiParam(value="课时对象", required = true)
            @RequestBody Video video){
        boolean result = videoService.save(video);
        if (result) {
            return ResultVO.ok().message("保存成功!");
        } else {
            return ResultVO.error().message("保存失败!");
        }
    }

    @ApiOperation("根据id查询课时")
    @GetMapping("/{id}")
    public ResultVO<Video> getById(
            @ApiParam(value="课时id", required = true)
            @PathVariable String id){

        Video video = videoService.getById(id);
        ResultVO<Video> resultVO = new ResultVO<>();
        return resultVO.success().data(video);
    }

    @ApiOperation("根据id修改课时")
    @PutMapping
    public ResultVO updateById(
            @ApiParam(value="课时对象", required = true)
            @RequestBody Video video){

        boolean result = videoService.updateById(video);
        if (result) {
            return ResultVO.ok().message("修改成功!");
        } else {
            return ResultVO.error().message("数据不存在!");
        }
    }

    @ApiOperation("根据ID删除课时")
    @DeleteMapping("/{id}")
    public ResultVO removeById(
            @ApiParam(value = "课时ID", required = true)
            @PathVariable String id){

        boolean result = videoService.removeVideoById(id);
        if (result) {
            return ResultVO.ok().message("删除成功!");
        } else {
            return ResultVO.error().message("数据不存在!");
        }
    }
}

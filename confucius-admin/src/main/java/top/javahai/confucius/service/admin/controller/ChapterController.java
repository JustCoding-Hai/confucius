package top.javahai.confucius.service.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.admin.entity.Chapter;
import top.javahai.confucius.service.admin.entity.vo.ChapterVO;
import top.javahai.confucius.service.admin.service.ChapterService;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("/admin/edu/chapters")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @ApiOperation("新增章节")
    @PostMapping
    public ResultVO save(
            @ApiParam(value="章节对象", required = true)
            @RequestBody Chapter chapter){
        boolean result = chapterService.save(chapter);
        if (result) {
            return ResultVO.ok().message("保存成功");
        } else {
            return ResultVO.error().message("保存失败");
        }
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("/{id}")
    public ResultVO<Chapter> getById(
            @ApiParam(value="章节id", required = true)
            @PathVariable String id){

        Chapter chapter = chapterService.getById(id);
        ResultVO<Chapter> resultVO = new ResultVO<>();
        return resultVO.success().data(chapter);
    }

    @ApiOperation("根据id修改章节")
    @PutMapping
    public ResultVO updateById(
            @ApiParam(value="章节对象", required = true)
            @RequestBody Chapter chapter){

        boolean result = chapterService.updateById(chapter);
        if (result) {
            return ResultVO.ok().message("修改成功");
        } else {
            return ResultVO.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID删除章节数据及章节相关联数据")
    @DeleteMapping("/{id}")
    public ResultVO removeById(
            @ApiParam(value = "章节ID", required = true)
            @PathVariable String id){

        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return ResultVO.ok().message("删除成功");
        } else {
            return ResultVO.error().message("删除过程出现了错误！");
        }
    }

    @ApiOperation("嵌套视频数据的章节数据列表")
    @GetMapping("/nested-list")
    public ResultVO<List<ChapterVO>> nestedListByCourseId(
            @ApiParam(value = "课程id", required = true)
            @RequestParam(value = "courseId") String courseId){

        List<ChapterVO> chapterVOList = chapterService.getNestedList(courseId);
        ResultVO<List<ChapterVO>> resultVO = new ResultVO<>();
        return resultVO.success().data(chapterVOList);
    }
}


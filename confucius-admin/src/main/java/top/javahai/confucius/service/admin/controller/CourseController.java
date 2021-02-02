package top.javahai.confucius.service.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.helper.ObjectHelper;
import top.javahai.confucius.frame.common.result.ResultPage;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.admin.entity.vo.*;
import top.javahai.confucius.service.admin.service.CourseService;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/admin/edu/courses")
public class CourseController {

    @Autowired
    CourseService courseService;

    @ApiOperation(value = "新增课程")
    @PostMapping
    public ResultVO<String> saveCourseInfo(
            @ApiParam(value = "课程表单信息",required = true)
            @RequestBody CourseFormVO courseFormVO){
        String courseId=courseService.saveCourseInfo(courseFormVO);
        return new ResultVO<String>().success().data(courseId).message("保存成功!");
    }


    @ApiOperation(value = "根据id获取课程信息")
    @GetMapping("/{id}")
    public ResultVO<CourseFormVO> getCourseInfoById(
            @ApiParam(value = "课程id",required = true)
            @PathVariable(value = "id") String id){
        CourseFormVO courseFormVO =courseService.getCourseInfoById(id);
        ResultVO<CourseFormVO> resultVO = new ResultVO<>();
        if (ObjectHelper.isEmpty(courseFormVO)){
            return resultVO.fail().data(null).message("数据不存在！");
        }
        return resultVO.success().data(courseFormVO);
    }

    @ApiOperation("更新课程")
    @PutMapping
    public ResultVO updateCourseInfoById(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseFormVO courseFormVO){

        courseService.updateCourseInfoById(courseFormVO);
        return ResultVO.ok("修改成功!");
    }

    @ApiOperation(value = "分页课程列表查询")
    @GetMapping("/page-list")
    public ResultVO<ResultPage> listPage(@ApiParam("当前页码") @RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @ApiParam("每页记录数") @RequestParam(value = "size",defaultValue = "10") Integer size,
                                         @ApiParam("查询条件") CourseQueryVO courseQueryVO){
        Page<CourseInfoVO> pageParam = new Page<>(page, size);
        Page<CourseInfoVO> coursePage = courseService.selectPage(pageParam,courseQueryVO);
        ResultPage resultPage = new ResultPage(coursePage.getTotal(), coursePage.getRecords());
        return new ResultVO<ResultPage>().success().data(resultPage);
    }

    @ApiOperation(value = "根据id删除课程及其所有的关联数据")
    @DeleteMapping("/{id}")
    public ResultVO deleteCourseById(@ApiParam("课程id") @PathVariable String id){


        boolean result = courseService.deleteCourseById(id);

        if (result){
            return ResultVO.ok("删除成功");
        }
        return ResultVO.error("删除失败");
    }

    @ApiOperation("根据ID获取课程发布信息")
    @GetMapping("/{id}/publish-info")
    public ResultVO<CoursePublishInfoVO> getCoursePublishVoById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String id){

        CoursePublishInfoVO coursePublishInfoVO = courseService.getCoursePublishVOById(id);
        return new ResultVO<CoursePublishInfoVO>().success().data(coursePublishInfoVO);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("/{id}/publish-info")
    public ResultVO publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){

        boolean result = courseService.publishCourseById(id);
        if (!result){
            return ResultVO.error("发布失败！");
        }
        return ResultVO.ok("发布成功！");
    }


}


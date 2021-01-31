package top.javahai.confucius.service.portal.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.portal.entity.Course;
import top.javahai.confucius.service.portal.entity.vo.CourseInfoVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseQueryVO;
import top.javahai.confucius.service.portal.entity.vo.WebCourseVO;
import top.javahai.confucius.service.portal.service.CourseService;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Api(tags="课程相关接口")
@RestController
@RequestMapping("api/portal/courses")

public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("课程列表")
    @GetMapping
    public ResultVO<List<Course>> list(
            @ApiParam(value = "查询对象") WebCourseQueryVO webCourseQueryVo){
        List<Course> courseList = courseService.webSelectCourseInfoList(webCourseQueryVo);
        return  new ResultVO<List<Course>>().success().data(courseList);
    }

    @ApiOperation("根据ID查询课程")
    @GetMapping("/{courseId}")
    public ResultVO<WebCourseVO> getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){

        WebCourseVO webCourseVO = courseService.selectWebCourseVoById(courseId);
        return new ResultVO<WebCourseVO>().success().data(webCourseVO);
    }

}


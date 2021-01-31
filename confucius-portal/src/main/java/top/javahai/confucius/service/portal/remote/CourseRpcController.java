package top.javahai.confucius.service.portal.remote;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.base.service.dto.CourseDTO;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.portal.service.CourseService;

/**
 * @author Hai
 * @program: confucius
 * @description: 课程远程接口调用控制器
 * @create 2021/1/27 - 22:42
 **/
@Api(value = "课程远程调用",tags = "Course RPC Controller")
@RestController
@RequestMapping("/portal/rpc/courses")
public class CourseRpcController {

    @Autowired
    CourseService courseService;

    @ApiOperation("根据课程id查询课程信息")
    @GetMapping("/course-dto/{courseId}")
    public CourseDTO getCourseDtoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){

        return courseService.getCourseDTOById(courseId);
    }

    @ApiOperation("根据课程id更改销售量")
    @GetMapping("/update-buy-count/{id}")
    public ResultVO updateBuyCountById(
            @ApiParam(value = "课程id", required = true)
            @PathVariable String id){
        courseService.updateBuyCountById(id);
        return ResultVO.ok();
    }


}

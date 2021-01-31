package top.javahai.confucius.service.portal.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.portal.entity.Course;
import top.javahai.confucius.service.portal.entity.Teacher;
import top.javahai.confucius.service.portal.entity.vo.HotDataVO;
import top.javahai.confucius.service.portal.service.CourseService;
import top.javahai.confucius.service.portal.service.TeacherService;

import java.util.List;

/**
 * @author Hai
 * @program: confucius
 * @description: 首页数据控制器
 * @create 2021/1/23 - 16:06
 **/
@Api(tags="首页数据")
@RestController
@RequestMapping("/api/portal/index")
public class IndexController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("热门数据")
    @GetMapping("/hot-data")
    public ResultVO<HotDataVO> index(){
        List<Course> courseList = courseService.selectHotCourse();
        List<Teacher> teacherList = teacherService.selectHotTeacher();
        HotDataVO hotDataVO = new HotDataVO();
        hotDataVO.setCourseList(courseList);
        hotDataVO.setTeacherList(teacherList);
        return new ResultVO<HotDataVO>().success().data(hotDataVO);
    }

}

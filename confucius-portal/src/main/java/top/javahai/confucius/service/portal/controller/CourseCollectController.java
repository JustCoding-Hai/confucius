package top.javahai.confucius.service.portal.controller;


import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.javahai.confucius.frame.common.entity.JwtInfo;
import top.javahai.confucius.frame.common.helper.JwtHelper;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.portal.entity.vo.CourseCollectVO;
import top.javahai.confucius.service.portal.service.CourseCollectService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 课程收藏 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Api(tags = "课程收藏")
@RestController
@RequestMapping("/api/portal/course-collect")
public class CourseCollectController {
    @Autowired
    private CourseCollectService courseCollectService;

    @ApiOperation(value = "判断是否收藏")
    @GetMapping("auth/is-collect/{courseId}")
    public ResultVO<Boolean> isCollect(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        Boolean isCollect = courseCollectService.isCollect(courseId, jwtInfo.getId());
        return new ResultVO<Boolean>().success().data(isCollect);
    }

    @ApiOperation(value = "收藏课程")
    @PostMapping("auth/save/{courseId}")
    public ResultVO save(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        courseCollectService.saveCourseCollect(courseId, jwtInfo.getId());
        return ResultVO.ok().message("收藏成功");
    }

    @ApiOperation(value = "获取课程收藏列表")
    @GetMapping("auth/list")
    public ResultVO<List<CourseCollectVO>> collectList(HttpServletRequest request) {

        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        List<CourseCollectVO> list = courseCollectService.selectListByMemberId(jwtInfo.getId());
        return new ResultVO<List<CourseCollectVO>>().success().data(list);
    }

    @ApiOperation(value = "取消收藏课程")
    @DeleteMapping("auth/remove/{courseId}")
    public ResultVO remove(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtHelper.getMemberIdByJwtToken(request);
        Boolean result = courseCollectService.removeCourseCollect(courseId, jwtInfo.getId());
        if (!result) {
            return ResultVO.error().message("取消失败");
        }
        return ResultVO.ok().message("已取消收藏");
    }

}


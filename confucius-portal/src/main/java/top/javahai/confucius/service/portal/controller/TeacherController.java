package top.javahai.confucius.service.portal.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.portal.entity.Teacher;
import top.javahai.confucius.service.portal.entity.vo.WebTeacherInfoVO;
import top.javahai.confucius.service.portal.service.TeacherService;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Api(tags = "讲师相关接口",value="讲师相关接口")
@RestController
@RequestMapping("api/portal/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value="所有讲师列表")
    @GetMapping
    public ResultVO<List<Teacher>> listAll(){
        List<Teacher> list = teacherService.list();
        return new ResultVO<List<Teacher>>().success().data(list);
    }

    @ApiOperation(value = "获取讲师详细信息")
    @GetMapping("/{id}")
    public ResultVO<WebTeacherInfoVO> getTeacherInfoById(
            @ApiParam(value = "讲师ID", required = true)
            @PathVariable String id) {
        WebTeacherInfoVO webTeacherInfoVO = teacherService.getTeacherInfoById(id);
        return new ResultVO<WebTeacherInfoVO>().success().data(webTeacherInfoVO);
    }

}


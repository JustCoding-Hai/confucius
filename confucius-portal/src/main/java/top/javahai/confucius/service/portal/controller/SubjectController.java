package top.javahai.confucius.service.portal.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.portal.entity.vo.SubjectVO;
import top.javahai.confucius.service.portal.service.SubjectService;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2021-01-21
 */
@Api(tags="课程分类相关接口")
@RestController
@RequestMapping("api/portal/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation("嵌套数据列表")
    @GetMapping("/nested-list")
    public ResultVO<List<SubjectVO>> nestedList(){
        List<SubjectVO> subjectVoList = subjectService.getNestedSubjectList();
        return new ResultVO<List<SubjectVO>>().success().data(subjectVoList);
    }
}


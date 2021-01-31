package top.javahai.confucius.service.education.controller.admin;


import ch.qos.logback.core.joran.spi.NoAutoStart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.javahai.confucius.frame.base.service.exception.BaseException;
import top.javahai.confucius.frame.common.result.ResultCodeEnum;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.frame.common.util.ExceptionUtils;
import top.javahai.confucius.service.education.entity.Subject;
import top.javahai.confucius.service.education.entity.vo.SubjectVO;
import top.javahai.confucius.service.education.service.SubjectService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Api(tags = "课程类别管理",value = "Subject Controller")
@Slf4j
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;


    @ApiOperation(value = "Excel批量导入课程类别")
    @PostMapping("/import")
    public ResultVO batchImport(
            @ApiParam(name = "ExcelFile",required = true)
            @RequestParam(value = "file") MultipartFile file){
        try {
            subjectService.batchImport(file.getInputStream());
            return ResultVO.ok("数据导入成功！");
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new BaseException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation(value = "获取嵌套的课程类别")
    @GetMapping("/list")
    public ResultVO<List<SubjectVO>> getNestedSubjectList(){
        List<SubjectVO> subjectVOList= subjectService.getNestedSubjectList();
        return new ResultVO<List<SubjectVO>>().success().data(subjectVOList);
    }



}


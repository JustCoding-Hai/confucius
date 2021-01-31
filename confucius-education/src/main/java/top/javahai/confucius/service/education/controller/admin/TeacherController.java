package top.javahai.confucius.service.education.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.javahai.confucius.frame.common.helper.ObjectHelper;
import top.javahai.confucius.service.education.entity.Teacher;
import top.javahai.confucius.service.education.entity.vo.TeacherQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.javahai.confucius.frame.common.result.ResultPage;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.education.service.TeacherService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static top.javahai.confucius.frame.common.result.ResultVO.error;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2020-11-15
 */
@Api(tags = "讲师管理相关接口")
@RestController
@RequestMapping("/admin/edu/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation("返回所有的讲师")
    @GetMapping
    public ResultVO<List<Teacher>> getAllTeachers(){
        return new ResultVO<List<Teacher>>().success().data(teacherService.list());
    }

    @ApiOperation(value = "根据id删除讲师",notes = "根据id删除讲师")
    @DeleteMapping("/{id}")
    public ResultVO deleteTeacherById(@ApiParam("讲师id") @PathVariable String id){

        String avatar = teacherService.getTeacherAvatarById(id);

        boolean result = teacherService.removeById(id);

        teacherService.removeAvatarByUrl(avatar);

        if (result){
            return ResultVO.ok("删除成功");
        }
        return ResultVO.error("删除失败");
    }

    @ApiOperation(value = "根据id列表删除讲师",notes = "根据id列表删除讲师")
    @DeleteMapping
    public ResultVO deleteTeacherByIds(@ApiParam("讲师id列表") @RequestBody List<String> idList){
        boolean result = teacherService.removeByIds(idList);

        if (result){
            return ResultVO.ok("删除成功");
        }
        return ResultVO.error("删除失败");
    }

    @ApiOperation(value = "分页讲师列表查询")
    @GetMapping("/page-list")
    public ResultVO<ResultPage> listPage(@ApiParam("当前页码") @RequestParam(value = "page",defaultValue = "1") Integer page,
                                         @ApiParam("每页记录数") @RequestParam(value = "size",defaultValue = "10") Integer size,
                                         @ApiParam("查询条件") TeacherQueryVO teacherQueryVO){
        Page<Teacher> pageParam = new Page<>(page, size);
        Page<Teacher> teacherPage = teacherService.selectPage(pageParam,teacherQueryVO);
        return new ResultVO<ResultPage>().success().data(new ResultPage(teacherPage.getTotal(),teacherPage.getRecords()));
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public ResultVO save(@ApiParam(value = "讲师信息对象",required = true) @RequestBody Teacher teacher){
        boolean result = teacherService.save(teacher);
        if(result){
            return ResultVO.ok("添加成功");
        }
        return error("添加失败");
    }
    @ApiOperation(value = "根据id修改讲师")
    @PutMapping
    public ResultVO update(
            @ApiParam(value = "讲师信息对象",required = true) @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return ResultVO.ok("修改成功！");
        }
        return error().message("找不到数据");
    }

    @ApiOperation(value = "根据id获取讲师")
    @GetMapping("/{id}")
    public ResultVO<Teacher> getTeacherById(@ApiParam(value = "讲师id",required = true)@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        ResultVO<Teacher> resultVO = new ResultVO<>();
        if (ObjectHelper.isEmpty(teacher)){
            return resultVO.fail().data(null);
        }
        return resultVO.success().data(teacher);
    }


    @ApiOperation(value = "根据左关键字查询讲师名列表")
    @GetMapping("/name")
    public ResultVO<List<Map<String,Object>>> getTeacherByKeyword(
            @ApiParam(value = "关键词",required = true)@RequestParam(value = "keyword") String keyword){
        List<Map<String,Object>> nameList=teacherService.getNameListByKeyword(keyword);
        return new ResultVO<List<Map<String,Object>>>().success().data(nameList);
    }





}


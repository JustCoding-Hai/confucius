package top.javahai.confucius.service.acl.controller;


import com.baomidou.mybatisplus.extension.api.R;
import top.javahai.confucius.frame.common.result.ResultPage;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.acl.entity.Role;
import top.javahai.confucius.service.acl.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public ResultVO<ResultPage> index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            Role role) {
        Page<Role> pageParam = new Page<>(page, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(role.getRoleName())) {
            wrapper.like("role_name",role.getRoleName());
        }
        roleService.page(pageParam,wrapper);
        ResultPage resultPage = new ResultPage(pageParam.getTotal(), pageParam.getRecords());
        return new ResultVO<ResultPage>().success().data(resultPage);
    }

    @ApiOperation(value = "获取角色")
    @GetMapping("get/{id}")
    public ResultVO<Role> get(@PathVariable String id) {
        Role role = roleService.getById(id);
        return new ResultVO<Role>().success().data(role);
    }

    @ApiOperation(value = "新增角色")
    @PostMapping("save")
    public ResultVO save(@RequestBody Role role) {
        roleService.save(role);
        return ResultVO.ok();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping("update")
    public ResultVO updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return ResultVO.ok();
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        roleService.removeById(id);
        return ResultVO.ok();
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public ResultVO batchRemove(@RequestBody List<String> idList) {
        roleService.removeByIds(idList);
        return ResultVO.ok();
    }
}


package top.javahai.confucius.service.acl.controller;


import com.baomidou.mybatisplus.extension.api.R;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.acl.entity.Permission;
import top.javahai.confucius.service.acl.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public ResultVO<List<Permission>> indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenu();
        return new ResultVO<List<Permission>>().success().data(list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public ResultVO remove(@PathVariable String id) {
        permissionService.removeChildById(id);
        return ResultVO.ok().message("删除成功！");
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public ResultVO doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRelationShip(roleId,permissionId);
        return ResultVO.ok().message("操作成功！");
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public ResultVO<List<Permission>> toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return new ResultVO<List<Permission>>().success().data(list);
    }

    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public ResultVO save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return ResultVO.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public ResultVO updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return ResultVO.ok();
    }

}


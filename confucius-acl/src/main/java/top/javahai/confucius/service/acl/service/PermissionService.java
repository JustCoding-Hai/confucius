package top.javahai.confucius.service.acl.service;

import com.alibaba.fastjson.JSONObject;
import top.javahai.confucius.service.acl.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 递归获取全部菜单
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * 根据角色获取菜单
     * @param roleId
     * @return
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * 递归删除菜单
     * @param id
     */
    void removeChildById(String id);

    /**
     * 根据用户id获取用户菜单
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);


    /**
     * 根据用户id获取用户菜单,返回Json
     * @param id
     * @return
     */
    List<JSONObject> selectPermissionByUserId(String id);

    /**
     * 给角色分配权限
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRelationShip(String roleId, String[] permissionId);
}

package top.javahai.confucius.service.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import top.javahai.confucius.frame.common.result.ResultVO;
import top.javahai.confucius.service.acl.entity.Permission;
import top.javahai.confucius.service.acl.service.IndexService;
import top.javahai.confucius.service.acl.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author ethan
 */
@RestController
@RequestMapping("/admin/acl/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public ResultVO<Map<String, Object>> info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return new ResultVO<Map<String, Object>>().success().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public ResultVO getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return ResultVO.ok().data( permissionList);
    }

    @PostMapping("logout")
    public ResultVO logout(){
        return ResultVO.ok().message("成功退出！");
    }

}

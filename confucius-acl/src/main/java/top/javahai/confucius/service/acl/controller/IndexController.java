//package top.javahai.confucius.service.acl.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.baomidou.mybatisplus.extension.api.R;
//import top.javahai.confucius.service.acl.entity.Permission;
//import top.javahai.confucius.service.acl.service.IndexService;
//import top.javahai.confucius.service.acl.service.PermissionService;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/admin/acl/index")
//public class IndexController {
//
//    @Autowired
//    private IndexService indexService;
//
//    /**
//     * 根据token获取用户信息
//     */
//    @GetMapping("info")
//    public R info(){
//        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        Map<String, Object> userInfo = indexService.getUserInfo(username);
//        return R.ok().data(userInfo);
//    }
//
//    /**
//     * 获取菜单
//     * @return
//     */
//    @GetMapping("menu")
//    public R getMenu(){
//        //获取当前登录用户用户名
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        List<JSONObject> permissionList = indexService.getMenu(username);
//        return R.ok().data("permissionList", permissionList);
//    }
//
//    @PostMapping("logout")
//    public R logout(){
//        return R.ok();
//    }
//
//}

package top.javahai.confucius.service.acl.service;

import top.javahai.confucius.service.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService extends IService<User> {

    /**
     * 从数据库中取出用户信息
     * @param username
     * @return
     */
    User selectByUsername(String username);
}

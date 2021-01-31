package top.javahai.confucius.frame.base.service.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * //EnableTransactionManagement开启事务管理
 * @author Hai
 * @program: zhkuschool-frame
 * @description: MyBatisPlus的配置类
 * @create 2020/11/15 - 14:29
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("top.javahai.confucius.service.*.mapper")
public class MyBatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

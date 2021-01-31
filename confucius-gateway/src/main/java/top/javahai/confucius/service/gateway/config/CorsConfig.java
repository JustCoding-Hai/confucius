package top.javahai.confucius.service.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.*;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @author Hai
 * @program: confucius
 * @description: 跨域配置
 * @create 2021/1/30 - 11:31
 **/
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsFilter() {
        //所有的方法，源，请求头都跨域
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        //配置路径匹配解析器
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        //所有的接口都遵循配置
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}

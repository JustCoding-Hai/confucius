package top.javahai.confucius.service.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/23 - 11:31
 **/
@Configuration
@EnableSwagger2
public class MySwaggerConfig {

    @Bean
    public Docket portalApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("portalApi")
                .apiInfo(portalApiInfo())
                .select()
                //选择显示的api路径
                .paths(PathSelectors.regex("/portal/.*"))
                .build();
    }

    private ApiInfo portalApiInfo(){
        return new ApiInfoBuilder()
                .title("门户系统API文档")
                .description("本文档描述了内容管理系统微服务接口定义")
                .version("1.0")
                .contact(new Contact("Ethan Huang","https://blog.csdn.net/huangjhai","1258398543@qq.com"))
                .build();
    }
}

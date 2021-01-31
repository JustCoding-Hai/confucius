package top.javahai.confucius.service.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/23 - 11:18
 **/
@SpringBootApplication
@ComponentScan({"top.javahai.confucius"})
@EnableDiscoveryClient
@EnableFeignClients
public class CmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsServiceApplication.class, args);
    }
}

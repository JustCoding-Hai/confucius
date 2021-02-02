package top.javahai.confucius.service.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/13 - 11:25
 **/
@SpringBootApplication
@ComponentScan({"top.javahai.confucius.service.admin",
        "top.javahai.confucius.frame.base.service"})
@EnableDiscoveryClient
@EnableFeignClients
public class AdminServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminServiceApplication.class, args);
    }

}

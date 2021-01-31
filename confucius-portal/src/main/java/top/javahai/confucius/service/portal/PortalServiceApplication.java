package top.javahai.confucius.service.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/22 - 23:05
 **/
@SpringBootApplication
@ComponentScan({"top.javahai.confucius.service.portal",
        "top.javahai.confucius.frame.base.service"})
@EnableDiscoveryClient
@EnableFeignClients
public class PortalServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalServiceApplication.class, args);
    }

}

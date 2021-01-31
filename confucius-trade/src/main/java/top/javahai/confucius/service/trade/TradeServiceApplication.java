package top.javahai.confucius.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/24 - 12:41
 **/
@SpringBootApplication
@ComponentScan({"top.javahai.confucius"})
@EnableDiscoveryClient
@EnableFeignClients
public class TradeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeServiceApplication.class, args);
    }
}

package top.javahai.confucius.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Hai
 * @program: confucius
 * @description:
 * @create 2021/1/31 - 16:51
 **/
@SpringBootApplication
@ComponentScan({"top.javahai.confucius"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StatisticsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsServiceApplication.class);
    }
}

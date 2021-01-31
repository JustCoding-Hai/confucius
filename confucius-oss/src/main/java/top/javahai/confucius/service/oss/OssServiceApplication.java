package top.javahai.confucius.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author hai
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"top.javahai.confucius"})
@EnableDiscoveryClient
public class OssServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssServiceApplication.class, args);
    }

}

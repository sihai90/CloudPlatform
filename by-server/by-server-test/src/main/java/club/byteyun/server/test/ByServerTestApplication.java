package club.byteyun.server.test;

import club.byteyun.common.annotation.EnableBYAuthExceptionHandler;
import club.byteyun.common.annotation.EnableBYOauth2FeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableFeignClients
@EnableDiscoveryClient //开启服务注册与发现
@EnableBYOauth2FeignClient
@SpringBootApplication
@EnableBYAuthExceptionHandler
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启Spring Cloud Security权限注解
public class ByServerTestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ByServerTestApplication.class, args);
    }

}

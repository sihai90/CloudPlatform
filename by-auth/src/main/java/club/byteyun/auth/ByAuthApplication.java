package club.byteyun.auth;

import club.byteyun.common.annotation.EnableBYAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ByAuthApplication
 * @Description //TODO 认证授权服务启动器
 * @Date 10:59 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@EnableDiscoveryClient //开启服务注册发现功能
@SpringBootApplication
@EnableBYAuthExceptionHandler
public class ByAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ByAuthApplication.class, args);
    }

}

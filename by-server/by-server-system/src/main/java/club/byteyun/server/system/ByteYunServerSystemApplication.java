package club.byteyun.server.system;

import club.byteyun.common.annotation.EnableByteYunAuthExceptionHandler;
import club.byteyun.common.annotation.EnableByteYunServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @ClassName ByteYunServerSystemApplication
 * @Description //TODO 系统服务模块
 * @Date 9:25 2020/4/30
 * @Author lql
 * @version 1.0
 **/
@EnableByteYunServerProtect //开启Zuul拦截所有请求
@EnableDiscoveryClient //开启服务注册与发现
@SpringBootApplication
@EnableByteYunAuthExceptionHandler
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启Spring Cloud Security权限注解
public class ByteYunServerSystemApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ByteYunServerSystemApplication.class, args);
    }

}

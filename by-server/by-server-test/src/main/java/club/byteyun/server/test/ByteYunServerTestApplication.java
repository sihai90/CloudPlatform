package club.byteyun.server.test;

import club.byteyun.common.annotation.EnableByteYunAuthExceptionHandler;
import club.byteyun.common.annotation.EnableByteYunCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableByteYunCloudApplication //开启微服务防护 + 开启带令牌的 Feign请求 + 认证类型异常 功能
@EnableFeignClients
@SpringBootApplication
@EnableByteYunAuthExceptionHandler // 认证类型异常翻译。
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启Spring Cloud Security权限注解
public class ByteYunServerTestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ByteYunServerTestApplication.class, args);
    }

}

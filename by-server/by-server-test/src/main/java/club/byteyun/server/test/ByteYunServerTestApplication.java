package club.byteyun.server.test;

import club.byteyun.common.annotation.EnableByteYunAuthExceptionHandler;
import club.byteyun.common.annotation.EnableByteYunAuth2FeignClient;
import club.byteyun.common.annotation.EnableByteYunServerProtect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableByteYunServerProtect // 开启微服务防护，避免客户端绕过网关直接请求微服务；
@EnableFeignClients
@EnableDiscoveryClient //开启服务注册与发现
@EnableByteYunAuth2FeignClient //开启带令牌的Feign请求，避免微服务内部调用出现401异常；
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

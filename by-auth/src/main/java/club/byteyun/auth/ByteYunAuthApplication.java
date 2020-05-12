package club.byteyun.auth;

import club.byteyun.common.annotation.EnableByteYunAuthExceptionHandler;
import club.byteyun.common.annotation.EnableByteYunCloudApplication;
import club.byteyun.common.annotation.EnableByteYunLettuceRedis;
import org.mybatis.spring.annotation.MapperScan;
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
@EnableByteYunLettuceRedis //引入开启Redis服务-自动注入到SpringIOC中
@EnableDiscoveryClient //开启服务注册发现功能
@SpringBootApplication
@EnableByteYunCloudApplication
@EnableByteYunAuthExceptionHandler
@MapperScan("club.byteyun.auth.mapper")
public class ByteYunAuthApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ByteYunAuthApplication.class, args);
    }

}

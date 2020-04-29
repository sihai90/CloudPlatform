package club.byteyun.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @ClassName ByRegisterApplication
 * @Description //TODO Eureka服务注册中心
 * @Date 10:02 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@EnableEurekaServer  //启动eureka服务
@SpringBootApplication
public class ByRegisterApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ByRegisterApplication.class, args);
    }

}

package club.byteyun.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName ByteYunGatewayApplication
 * @Description //TODO  网关服务启动器
 * @Date 14:54 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ByteYunGatewayApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ByteYunGatewayApplication.class, args);
    }

}

package club.byteyun.server.test.service.fallback;

import club.byteyun.server.test.service.IHelloService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @ClassName HelloServiceFallback
 * @Description //TODO feign调用发送异常，回掉服务
 * @Date 17:13 2020/4/30
 * @Author lql
 * @version 1.0
 **/
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService>
{
    @Override
    public IHelloService create(Throwable throwable)
    {
        return name -> {
            log.error("调用by-server-system服务出错", throwable);
            return "调用出错";
        };
    }
}
package club.byteyun.server.test.service;

import club.byteyun.common.entity.BYServerConstant;
import club.byteyun.server.test.service.fallback.HelloServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = BYServerConstant.BY_SERVER_SYSTEM, contextId = "helloServiceClient", fallbackFactory = HelloServiceFallback .class)
public interface IHelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}
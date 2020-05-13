package club.byteyun.server.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @ClassName TestController
 * @Description //TODO 测试接口
 * @Date 11:41 2020/4/30
 * @Author lql
 * @version 1.0
 **/
@RestController
public class TestController {


    @GetMapping("hello")
    public String hello(String name) {
        return "hello" + name;
    }

    @GetMapping("info")
    public String test(){
        return "by-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
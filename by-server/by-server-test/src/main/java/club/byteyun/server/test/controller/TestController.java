package club.byteyun.server.test.controller;

import club.byteyun.server.test.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{

    @Autowired
    private IHelloService helloService;

    @GetMapping("hello")
    public String hello(String name)
    {
        return this.helloService.hello(name);
    }

}
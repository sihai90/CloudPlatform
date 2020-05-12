package club.byteyun.common.configure;

import club.byteyun.common.interceptor.BYServerProtectInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName BYServerProtectConfigure
 * @Description //TODO  将拦截器注入到spring容器中
 * @Date 11:34 2020/5/12
 * @Author lql
 * @version 1.0
 **/
public class BYServerProtectConfigure implements WebMvcConfigurer
{

    @Bean
    public HandlerInterceptor bYServerProtectInterceptor() {
        return new BYServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bYServerProtectInterceptor());
    }
}
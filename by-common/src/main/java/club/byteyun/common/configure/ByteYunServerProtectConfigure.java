package club.byteyun.common.configure;

import club.byteyun.common.interceptor.ByteYunServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @version 1.0
 * @ClassName ByteYunServerProtectConfigure
 * @Description //TODO  将拦截器注入到spring容器中
 * @Date 11:34 2020/5/12
 * @Author lql
 **/
public class ByteYunServerProtectConfigure implements WebMvcConfigurer
{


    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HandlerInterceptor bYServerProtectInterceptor()
    {
        return new ByteYunServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(bYServerProtectInterceptor());
    }


}
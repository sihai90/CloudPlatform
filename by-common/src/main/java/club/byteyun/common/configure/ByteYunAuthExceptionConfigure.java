package club.byteyun.common.configure;

import club.byteyun.common.handler.ByteYunAccessDeniedHandler;
import club.byteyun.common.handler.ByteYunAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName ByteYunAuthExceptionConfigure
 * @Description //TODO 
 * @Date 14:03 2020/5/12
 * @Author lql
 * @version 1.0
 **/
public class ByteYunAuthExceptionConfigure
{

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public ByteYunAccessDeniedHandler accessDeniedHandler()
    {
        return new ByteYunAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public ByteYunAuthExceptionEntryPoint authenticationEntryPoint()
    {
        return new ByteYunAuthExceptionEntryPoint();
    }
}
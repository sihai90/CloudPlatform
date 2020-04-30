package club.byteyun.common.configure;

import club.byteyun.common.handler.BYAccessDeniedHandler;
import club.byteyun.common.handler.BYAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class BYAuthExceptionConfigure
{

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public BYAccessDeniedHandler accessDeniedHandler()
    {
        return new BYAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public BYAuthExceptionEntryPoint authenticationEntryPoint()
    {
        return new BYAuthExceptionEntryPoint();
    }
}
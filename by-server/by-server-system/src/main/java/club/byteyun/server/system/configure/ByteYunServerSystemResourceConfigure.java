package club.byteyun.server.system.configure;

import club.byteyun.common.handler.ByteYunAccessDeniedHandler;
import club.byteyun.common.handler.ByteYunAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @ClassName ByteYunServerSystemResourceConfigure
 * @Description //TODO 资源服务器配置类
 * @Date 11:33 2020/4/30
 * @Author lql
 * @version 1.0
 **/
@EnableResourceServer
@Configuration
public class ByteYunServerSystemResourceConfigure extends ResourceServerConfigurerAdapter
{
    @Autowired
    private ByteYunAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private ByteYunAuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        //关闭csrf功能，配置所有的请求都必须携带令牌访问
        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception
    {
        resources.authenticationEntryPoint(authExceptionEntryPoint)
                 .accessDeniedHandler(accessDeniedHandler);
    }
}

package club.byteyun.auth.configure;

import club.byteyun.common.handler.BYAccessDeniedHandler;
import club.byteyun.common.handler.BYAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @ClassName BYResourceServerConfigure
 * @Description //TODO  资源服务器配置类
 * @Date 11:35 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@Configuration
@EnableResourceServer //开启资源服务器
public class BYResourceServerConfigure extends ResourceServerConfigurerAdapter
{

    @Autowired
    private BYAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private BYAuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        //关闭csrf安全防御,当前配置表示所有的请求路径都有效果
        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception
    {
        resources.accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authExceptionEntryPoint);
    }
}

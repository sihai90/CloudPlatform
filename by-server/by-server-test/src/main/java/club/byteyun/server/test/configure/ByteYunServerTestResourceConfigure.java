package club.byteyun.server.test.configure;

import club.byteyun.common.handler.ByteYunAccessDeniedHandler;
import club.byteyun.common.handler.ByteYunAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ByteYunServerTestResourceConfigure extends ResourceServerConfigurerAdapter
{

    @Autowired
    private ByteYunAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private ByteYunAuthExceptionEntryPoint authExceptionEntryPoint;


    @Override
    public void configure(HttpSecurity http) throws Exception
    {
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
        resources.accessDeniedHandler(accessDeniedHandler)
                 .authenticationEntryPoint(authExceptionEntryPoint);
    }
}

package club.byteyun.server.test.configure;

import club.byteyun.common.handler.BYAccessDeniedHandler;
import club.byteyun.common.handler.BYAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class BYServerTestResourceConfigure extends ResourceServerConfigurerAdapter
{

    @Autowired
    private BYAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private BYAuthExceptionEntryPoint authExceptionEntryPoint;


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

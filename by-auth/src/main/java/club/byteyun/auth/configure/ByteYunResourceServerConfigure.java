package club.byteyun.auth.configure;

import club.byteyun.common.handler.ByteYunAccessDeniedHandler;
import club.byteyun.common.handler.ByteYunAuthExceptionEntryPoint;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @ClassName ByteYunResourceServerConfigure
 * @Description //TODO  资源服务器配置类
 * @Date 11:35 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@Configuration
@EnableResourceServer //开启资源服务器
public class ByteYunResourceServerConfigure extends ResourceServerConfigurerAdapter
{

    @Autowired
    private ByteYunAuthProperties properties;

    @Autowired
    private ByteYunAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private ByteYunAuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        //获取到配置的免认证URL
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        //关闭csrf安全防御,当前配置表示所有的请求路径都有效果
        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
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

package club.byteyun.gateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * @ClassName BYGatewaySecurityConfigure
 * @Description //TODO 网关配置安全类
 * @Date 15:05 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@EnableWebSecurity
public class BYGatewaySecurityConfigure extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();
    }
}

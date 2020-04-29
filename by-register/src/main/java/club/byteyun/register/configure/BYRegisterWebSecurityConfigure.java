package club.byteyun.register.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName BYRegisterWebSecurityConfigure
 * @Description //TODO springsecurity配置
 * @Date 10:26 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@EnableWebSecurity
public class BYRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().ignoringAntMatchers("/eureka/**"); //开启eureka服务端的安全模式
        super.configure(http);
    }
}

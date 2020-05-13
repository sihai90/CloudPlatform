package club.byteyun.register.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @version 1.0
 * @ClassName ByteYunRegisterWebSecurityConfigure
 * @Description //TODO springsecurity配置
 * @Date 10:26 2020/4/29
 * @Author lql
 **/
@EnableWebSecurity
public class ByteYunRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().ignoringAntMatchers("/eureka/**")
                .and()
                .authorizeRequests().antMatchers("/actuator/**")
                .permitAll();
        super.configure(http);
    }
}

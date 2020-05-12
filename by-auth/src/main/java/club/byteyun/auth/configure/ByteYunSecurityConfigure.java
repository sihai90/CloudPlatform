package club.byteyun.auth.configure;

import club.byteyun.auth.service.ByteYunUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @version 1.0
 * @ClassName ByteYunSecurityConfigure
 * @Description //TODO 配置授权中心资源保护类
 * @Date 11:14 2020/4/29
 * @Author lql
 **/
@Order(2)
@EnableWebSecurity
public class ByteYunSecurityConfigure extends WebSecurityConfigurerAdapter
{
    @Autowired
    private ByteYunUserDetailService byteYunUserDetailService;
    /**
     * 配置密码加密方式
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        //过滤请求只对/oauth/请求有效果
        http.requestMatchers()
                .antMatchers("/oauth/**")
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .authenticated()
                .and()
                .csrf();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(byteYunUserDetailService).passwordEncoder(passwordEncoder());
    }


}

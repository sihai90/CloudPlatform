package club.byteyun.auth.configure;

import club.byteyun.auth.service.BYUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @ClassName BYAuthorizationServerConfigure
 * @Description //TODO 认证服务器相关的安全配置类
 * @Date 11:43 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@Configuration
@EnableAuthorizationServer
public class BYAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter
{
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private  BYUserDetailService byUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        clients.inMemory()
                .withClient("by-cloud")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("password","refresh_token")
                .scopes("all");
    }

    /**
     * 客户端从认证服务器获取令牌
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(byUserDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices());
    }

    /**
     * token存储方式
     * @return
     */
    @Bean
    public TokenStore tokenStore (){
        return  new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 配置token刷新过期方式
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices()
    {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore()); //设置token存储方式
        tokenServices.setSupportRefreshToken(true);//支持token刷新
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24); // 设置token有效时间
        tokenServices.setRefreshTokenValiditySeconds(60 *60 *24 * 7); // 设置刷新时间
        return  tokenServices;

    }
}

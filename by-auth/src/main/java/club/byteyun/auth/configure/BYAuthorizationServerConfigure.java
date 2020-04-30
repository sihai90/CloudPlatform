package club.byteyun.auth.configure;

import club.byteyun.auth.properties.BYClientsProperties;
import club.byteyun.auth.service.BYUserDetailService;
import club.byteyun.auth.translator.BYWebResponseExceptionTranslator;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
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
    @Autowired
    private BYAuthProperties byAuthProperties;
    @Autowired
    private BYWebResponseExceptionTranslator translator;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        BYClientsProperties[] authPropertiesClients = byAuthProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if(ArrayUtils.isNotEmpty(authPropertiesClients))
        {
            for (BYClientsProperties client : authPropertiesClients) {
                if (StringUtils.isBlank(client.getClient())) {
                    throw new Exception("client不能为空");
                }
                if (StringUtils.isBlank(client.getSecret())) {
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }
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
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(translator); //指定异常翻译器
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
        tokenServices.setAccessTokenValiditySeconds(byAuthProperties.getAccessTokenValiditySeconds()); // 设置token有效时间
        tokenServices.setRefreshTokenValiditySeconds(byAuthProperties.getRefreshTokenValiditySeconds()); // 设置刷新时间
        return  tokenServices;

    }
}

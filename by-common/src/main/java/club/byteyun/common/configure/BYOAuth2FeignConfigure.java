package club.byteyun.common.configure;

import club.byteyun.common.entity.BYConstant;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @ClassName BYOAuth2FeignConfigure
 * @Description //TODO 拦截Feign请求，加上令牌token
 * @Date 17:44 2020/4/30
 * @Author lql
 * @version 1.0
 **/
public class BYOAuth2FeignConfigure
{
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            //添加 Zuul Token
            String zuulToken = new String(Base64Utils.encode(BYConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(BYConstant.ZUUL_TOKEN_HEADER,zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}

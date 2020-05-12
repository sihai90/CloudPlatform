package club.byteyun.auth.filter;

import club.byteyun.auth.service.ValidateCodeService;
import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.exception.ValidateCodeException;
import club.byteyun.common.utils.ByteYunUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @ClassName ValidateCodeFilter
 * @Description //TODO  验证码过滤器
 * @Date 18:02 2020/5/12
 * @Author lql
 **/
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter
{

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException
    {
        RequestMatcher matcher = new AntPathRequestMatcher("/oauth/token", HttpMethod.POST.toString());
        if (matcher.matches(httpServletRequest) && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter("grant_type"), "password"))
        {
            try
            {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
            catch (ValidateCodeException e)
            {
                ByteYunResponse febsResponse = new ByteYunResponse();
                ByteYunUtils.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, febsResponse.message(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        }
        else
        {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException
    {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");
        validateCodeService.check(key, code);
    }
}
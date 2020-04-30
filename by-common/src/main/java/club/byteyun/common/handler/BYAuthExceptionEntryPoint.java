package club.byteyun.common.handler;

import club.byteyun.common.entity.BYResponse;
import club.byteyun.common.utils.BYUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @ClassName BYAuthExceptionEntryPoint
 * @Description //TODO 统一异常处理器
 * @Date 15:21 2020/4/30
 * @Author lql
 **/
public class BYAuthExceptionEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException
    {
        BYResponse response = new BYResponse();
        BYUtils.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_UNAUTHORIZED, response.message("token无效"));
    }
}

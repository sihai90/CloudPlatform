package club.byteyun.common.handler;

import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.utils.ByteYunUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @version 1.0
 * @ClassName ByteYunAuthExceptionEntryPoint
 * @Description //TODO 统一异常处理器
 * @Date 15:21 2020/4/30
 * @Author lql
 **/
public class ByteYunAuthExceptionEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException
    {
        ByteYunResponse response = new ByteYunResponse();
        ByteYunUtils.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_UNAUTHORIZED, response.message("token无效"));
    }
}

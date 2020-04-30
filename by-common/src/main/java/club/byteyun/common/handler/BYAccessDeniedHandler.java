package club.byteyun.common.handler;

import club.byteyun.common.entity.BYResponse;
import club.byteyun.common.utils.BYUtils;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName BYAccessDeniedHandler
 * @Description //TODO 处理403异常
 * @Date 15:51 2020/4/30
 * @Author lql
 * @version 1.0
 **/
public class BYAccessDeniedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException
    {
        BYResponse byResponse = new BYResponse();
        BYUtils.makeResponse(httpServletResponse,
                MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_FORBIDDEN,
                byResponse.message("没有权限访问该资源"));
    }
}

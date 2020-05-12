package club.byteyun.common.interceptor;

import club.byteyun.common.entity.ByteYunConstant;
import club.byteyun.common.entity.ByteYunResponse;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @ClassName ByteYunServerProtectInterceptor
 * @Description //TODO 定义拦截器，拦截所有web请求
 * @Date 11:31 2020/5/12
 * @Author lql
 * @version 1.0
 **/
public class ByteYunServerProtectInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
    {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(ByteYunConstant.ZUUL_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(ByteYunConstant.ZUUL_TOKEN_VALUE.getBytes()));
        // 校验 Zuul Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            return true;
        } else {
            ByteYunResponse febsResponse = new ByteYunResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(febsResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}
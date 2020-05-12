package club.byteyun.gateway.filter;

import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.utils.ByteYunUtils;
import com.netflix.zuul.context.RequestContext;
import io.lettuce.core.dynamic.support.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @ClassName ByteYunGatewayErrorFilter
 * @Description //TODO Zuul网关异常统一处理、
 * @Date 16:59 2020/4/30
 * @Author lql
 **/
@Slf4j
@Component
public class ByteYunGatewayErrorFilter extends SendErrorFilter
{

    @Override
    public Object run()
    {
        try
        {
            ByteYunResponse ByteYunResponse = new ByteYunResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            ByteYunResponse = resolveExceptionMessage(message, serviceId, ByteYunResponse);

            HttpServletResponse response = ctx.getResponse();
            ByteYunUtils.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ByteYunResponse);
            log.error("Zull sendError：{}", ByteYunResponse.getMessage());
        }
        catch (Exception ex)
        {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private ByteYunResponse resolveExceptionMessage(String message, String serviceId, ByteYunResponse ByteYunResponse)
    {
        if (StringUtils.containsIgnoreCase(message, "time out"))
        {
            return ByteYunResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error"))
        {
            return ByteYunResponse.message(serviceId + "服务不可用");
        }
        return ByteYunResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
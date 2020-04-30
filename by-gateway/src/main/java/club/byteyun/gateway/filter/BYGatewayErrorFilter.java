package club.byteyun.gateway.filter;

import club.byteyun.common.entity.BYResponse;
import club.byteyun.common.utils.BYUtils;
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
 * @ClassName BYGatewayErrorFilter
 * @Description //TODO Zuul网关异常统一处理、
 * @Date 16:59 2020/4/30
 * @Author lql
 **/
@Slf4j
@Component
public class BYGatewayErrorFilter extends SendErrorFilter
{

    @Override
    public Object run()
    {
        try
        {
            BYResponse BYResponse = new BYResponse();
            RequestContext ctx = RequestContext.getCurrentContext();
            String serviceId = (String) ctx.get(FilterConstants.SERVICE_ID_KEY);

            ExceptionHolder exception = findZuulException(ctx.getThrowable());
            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            BYResponse = resolveExceptionMessage(message, serviceId, BYResponse);

            HttpServletResponse response = ctx.getResponse();
            BYUtils.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, BYResponse);
            log.error("Zull sendError：{}", BYResponse.getMessage());
        }
        catch (Exception ex)
        {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private BYResponse resolveExceptionMessage(String message, String serviceId, BYResponse BYResponse)
    {
        if (StringUtils.containsIgnoreCase(message, "time out"))
        {
            return BYResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error"))
        {
            return BYResponse.message(serviceId + "服务不可用");
        }
        return BYResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
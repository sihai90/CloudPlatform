package club.byteyun.auth.handler;

import club.byteyun.common.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version 1.0
 * @ClassName GlobalExceptionHandler
 * @Description //TODO 授权服务全局异常处理器
 * @Date 17:05 2020/4/30
 * @Author lql
 **/
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler
{
}
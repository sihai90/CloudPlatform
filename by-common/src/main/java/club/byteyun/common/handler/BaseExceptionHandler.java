package club.byteyun.common.handler;

import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.exception.ByteYunAuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.message.AuthException;

/**
 * @version 1.0
 * @ClassName BaseExceptionHandler
 * @Description //TODO 统一异常处理器
 * @Date 17:04 2020/4/30
 * @Author lql
 **/
@Slf4j
public class BaseExceptionHandler
{

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ByteYunResponse handleException(Exception e)
    {
        log.error("系统内部异常，异常信息", e);
        return new ByteYunResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = ByteYunAuthException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ByteYunResponse handleFebsAuthException(AuthException e)
    {
        log.error("系统错误", e);
        return new ByteYunResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ByteYunResponse handleAccessDeniedException()
    {
        return new ByteYunResponse().message("没有权限访问该资源");
    }
}
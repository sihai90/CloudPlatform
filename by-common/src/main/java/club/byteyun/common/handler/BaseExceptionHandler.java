package club.byteyun.common.handler;

import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.exception.ByteYunAuthException;
import club.byteyun.common.exception.ByteYunException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.message.AuthException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Set;

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

    @ExceptionHandler(value = ByteYunException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ByteYunResponse handleFebsException(ByteYunException e)
    {
        log.error("系统错误", e);
        return new ByteYunResponse().message(e.getMessage());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ByteYunResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new ByteYunResponse().message(message.toString());
    }
}
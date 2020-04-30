package club.byteyun.common.annotation;

import club.byteyun.common.configure.BYAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableBYAuthExceptionHandler
 * @Description //TODO 注解实现资源服务器异常处理
 * @Date 16:19 2020/4/30
 * @Author lql
 * @version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BYAuthExceptionConfigure.class)
public @interface EnableBYAuthExceptionHandler {

}
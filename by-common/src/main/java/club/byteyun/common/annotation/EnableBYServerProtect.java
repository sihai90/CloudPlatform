package club.byteyun.common.annotation;

import club.byteyun.common.configure.BYServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableBYServerProtect
 * @Description //TODO  定义一个@Enable注解让配置类生效
 * @Date 11:35 2020/5/12
 * @Author lql
 * @version 1.0
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BYServerProtectConfigure.class)
public @interface EnableBYServerProtect
{

}
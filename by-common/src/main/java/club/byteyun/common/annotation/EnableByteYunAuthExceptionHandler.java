package club.byteyun.common.annotation;

import club.byteyun.common.configure.ByteYunAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ByteYunAuthExceptionConfigure.class)
public @interface EnableByteYunAuthExceptionHandler
{

}
package club.byteyun.common.annotation;

import club.byteyun.common.configure.ByteYunLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ByteYunLettuceRedisConfigure.class)
public @interface EnableByteYunLettuceRedis
{

}
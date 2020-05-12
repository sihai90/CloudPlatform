package club.byteyun.common.annotation;

import club.byteyun.common.configure.ByteYunAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ByteYunAuth2FeignConfigure.class)
public @interface EnableByteYunAuth2FeignClient
{

}
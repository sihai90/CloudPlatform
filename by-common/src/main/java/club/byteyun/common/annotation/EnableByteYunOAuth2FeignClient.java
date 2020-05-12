package club.byteyun.common.annotation;

import club.byteyun.common.configure.ByteYunOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ByteYunOAuth2FeignConfigure.class)
public @interface EnableByteYunOAuth2FeignClient
{

}
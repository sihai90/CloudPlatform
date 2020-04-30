package club.byteyun.common.annotation;

import club.byteyun.common.configure.BYOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BYOAuth2FeignConfigure.class)
public @interface EnableBYOauth2FeignClient {

}
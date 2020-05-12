package club.byteyun.common.annotation;

import club.byteyun.common.configure.ByteYunServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ByteYunServerProtectConfigure.class)
public @interface EnableByteYunServerProtect
{

}
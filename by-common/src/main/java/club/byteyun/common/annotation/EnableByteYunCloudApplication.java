package club.byteyun.common.annotation;

import club.byteyun.common.selector.ByteYunCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ByteYunCloudApplicationSelector.class)
public @interface EnableByteYunCloudApplication
{
}

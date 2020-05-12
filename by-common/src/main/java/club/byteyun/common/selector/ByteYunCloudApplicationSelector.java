package club.byteyun.common.selector;

import club.byteyun.common.configure.ByteYunAuthExceptionConfigure;
import club.byteyun.common.configure.ByteYunOAuth2FeignConfigure;
import club.byteyun.common.configure.ByteYunServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @ClassName ByteYunCloudApplicationSelector
 * @Description //TODO 将开启微服务防护 + 开启带令牌的Feign请求 + 认证类型异常翻译 注册到 SpringIOC容器中
 * @Date 14:41 2020/5/12
 * @Author lql
 * @version 1.0
 **/
public class ByteYunCloudApplicationSelector implements ImportSelector
{

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata)
    {
        return new String[]{
                ByteYunOAuth2FeignConfigure.class.getName(),
                ByteYunAuthExceptionConfigure.class.getName(),
                ByteYunServerProtectConfigure.class.getName()
        };
    }
}

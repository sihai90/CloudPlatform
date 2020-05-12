package club.byteyun.common.selector;

import club.byteyun.common.annotation.EnableByteYunAuth2FeignClient;
import club.byteyun.common.annotation.EnableByteYunAuthExceptionHandler;
import club.byteyun.common.annotation.EnableByteYunServerProtect;
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
                EnableByteYunAuth2FeignClient.class.getName(),
                EnableByteYunAuthExceptionHandler.class.getName(),
                EnableByteYunServerProtect.class.getName()
        };
    }
}

package club.byteyun.server.system.configure;

import club.byteyun.server.system.properties.ByteYunServerSystemProperties;
import club.byteyun.server.system.properties.ByteYunSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @ClassName ByteYunWebConfigure
 * @Description //TODO
 * @Date 14:24 2020/5/13
 * @Author lql
 * @version 1.0
 **/
@Configuration
@EnableSwagger2
public class ByteYunWebConfigure
{

    @Autowired
    private ByteYunServerSystemProperties properties;
    @Bean
    public Docket swaggerApi() {
        ByteYunSwaggerProperties swagger = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger));
    }

    private ApiInfo apiInfo(ByteYunSwaggerProperties swagger) {
        return new ApiInfo(
                swagger.getTitle(),
                swagger.getDescription(),
                swagger.getVersion(),
                null,
                new Contact(swagger.getAuthor(), swagger.getUrl(), swagger.getEmail()),
                swagger.getLicense(), swagger.getLicenseUrl(), Collections.emptyList());
    }
}

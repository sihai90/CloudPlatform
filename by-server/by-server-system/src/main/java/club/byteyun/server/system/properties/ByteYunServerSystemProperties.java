package club.byteyun.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:byteyun-server-system.properties"})
@ConfigurationProperties(prefix = "byteyun.server.system")
public class ByteYunServerSystemProperties
{
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private ByteYunSwaggerProperties swagger = new ByteYunSwaggerProperties();
}

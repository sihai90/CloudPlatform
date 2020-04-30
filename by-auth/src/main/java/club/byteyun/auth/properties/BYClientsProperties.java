package club.byteyun.auth.properties;

import lombok.Data;

/**
 * @ClassName BYClientsProperties
 * @Description //TODO  配置客户端信息
 * @Date 14:52 2020/4/30
 * @Author lql
 * @version 1.0
 **/
@Data
public class BYClientsProperties
{
    private String client; // client_id

    private String secret;

    private String grantType = "password,authorization_code,refresh_token"; //采用密码方式

    private String scope = "all"; //认证范围

}

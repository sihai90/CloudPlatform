package club.byteyun.auth.controller;

import club.byteyun.common.entity.BYResponse;
import club.byteyun.common.exception.BYAuthException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
/**
 * @ClassName SecurityController
 * @Description //TODO 授权测试接口
 * @Date 13:42 2020/4/29
 * @Author lql
 * @version 1.0
 **/
@RestController
public class SecurityController
{
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth()
    {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal)
    {
        return principal;
    }

    @DeleteMapping("signout")
    public BYResponse signout(HttpServletRequest request) throws BYAuthException
    {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        BYResponse byResponse = new BYResponse();
        if (!consumerTokenServices.revokeToken(token))
        {
            throw new BYAuthException("退出登录失败");
        }
        return byResponse.message("退出登录成功");
    }
}

package club.byteyun.auth.controller;

import club.byteyun.auth.service.ValidateCodeService;
import club.byteyun.common.entity.ByteYunResponse;
import club.byteyun.common.exception.ByteYunAuthException;
import club.byteyun.common.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @version 1.0
 * @ClassName SecurityController
 * @Description //TODO 授权测试接口
 * @Date 13:42 2020/4/29
 * @Author lql
 **/
@RestController
public class SecurityController
{
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @Autowired
    private ValidateCodeService validateCodeService;


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
    public ByteYunResponse signout(HttpServletRequest request) throws ByteYunAuthException
    {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        ByteYunResponse byteYunResponse = new ByteYunResponse();
        if (!consumerTokenServices.revokeToken(token))
        {
            throw new ByteYunAuthException("退出登录失败");
        }
        return byteYunResponse.message("退出登录成功");
    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ValidateCodeException
     */
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException
    {
        validateCodeService.create(request, response);
    }


}

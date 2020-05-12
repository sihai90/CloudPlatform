package club.byteyun.auth.service;

import club.byteyun.auth.configure.ByteYunAuthProperties;
import club.byteyun.auth.properties.ValidateCodeProperties;
import club.byteyun.common.entity.ByteYunConstant;
import club.byteyun.common.exception.ValidateCodeException;
import club.byteyun.common.service.ByteYunRedisService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ValidateCodeService
 * @Description //TODO 验证码服务
 * @Date 17:44 2020/5/12
 * @Author lql
 * @version 1.0
 **/
@Service
public class ValidateCodeService
{

    @Autowired
    private ByteYunRedisService byteYunRedisService;
    @Autowired
    private ByteYunAuthProperties byteYunAuthProperties;

    /**
     * 生成验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException
    {
        String key = request.getParameter("key");
        if (StringUtils.isBlank(key))
        {
            throw new ValidateCodeException("验证码key不能为空");
        }
        ValidateCodeProperties code = byteYunAuthProperties.getCode();
        setHeader(response, code.getType());

        Captcha captcha = createCaptcha(code);
        byteYunRedisService.set(ByteYunConstant.CODE_PREFIX + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    /**
     * 校验验证码
     *
     * @param key   前端上送 key
     * @param value 前端上送待校验值
     */
    public void check(String key, String value) throws ValidateCodeException
    {
        Object codeInRedis = byteYunRedisService.get(ByteYunConstant.CODE_PREFIX + key);
        if (StringUtils.isBlank(value))
        {
            throw new ValidateCodeException("请输入验证码");
        }
        if (codeInRedis == null)
        {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsIgnoreCase(value, String.valueOf(codeInRedis)))
        {
            throw new ValidateCodeException("验证码不正确");
        }
    }

    private Captcha createCaptcha(ValidateCodeProperties code)
    {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), ByteYunConstant.GIF))
        {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        else
        {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    private void setHeader(HttpServletResponse response, String type)
    {
        if (StringUtils.equalsIgnoreCase(type, ByteYunConstant.GIF))
        {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        }
        else
        {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}
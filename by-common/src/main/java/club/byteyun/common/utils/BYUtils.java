package club.byteyun.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName BYUtils
 * @Description //TODO HttpServletResponse 工具类
 * @Date 15:27 2020/4/30
 * @Author lql
 * @version 1.0
 **/
public class BYUtils
{

    /**
     * 设置响应
     *
     * @param response    HttpServletResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param value       响应内容
     * @throws IOException IOException
     */
    public static void makeResponse(HttpServletResponse response, String contentType, int status, Object value) throws IOException
    {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
    }
}

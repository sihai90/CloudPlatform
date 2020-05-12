package club.byteyun.common.entity;

import java.util.HashMap;

/**
 * @ClassName ByteYunResponse
 * @Description //TODO 通用响应结果
 * @Date 13:35 2020/4/29
 * @Author lql
 * @version 1.0
 **/
public class ByteYunResponse extends HashMap<String, Object>
{
    private static final long serialVersionUID = -8713837118340960775L;

    public ByteYunResponse message(String message)
    {
        this.put("message", message);
        return this;
    }

    public ByteYunResponse data(Object data)
    {
        this.put("data", data);
        return this;
    }

    @Override
    public ByteYunResponse put(String key, Object value)
    {
        super.put(key, value);
        return this;
    }

    public String getMessage()
    {
        return String.valueOf(get("message"));
    }

    public Object getData()
    {
        return get("data");
    }
}

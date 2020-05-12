package club.byteyun.common.entity;

/**
 * @ClassName ByteYunConstant
 * @Description //TODO Zuul常量
 * @Date 11:29 2020/5/12
 * @Author lql
 * @version 1.0
 **/
public class ByteYunConstant
{

    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    public static final String ZUUL_TOKEN_HEADER = "ZuulToken";
    /**
     * Zuul请求头TOKEN值
     */
    public static final String ZUUL_TOKEN_VALUE = "by-cloud:zuul:123456";

    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "byteyun.captcha.";
}

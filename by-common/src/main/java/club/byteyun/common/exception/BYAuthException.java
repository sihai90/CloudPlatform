package club.byteyun.common.exception;

/**
 * @ClassName BYAuthException
 * @Description //TODO  自定义异常AuthException
 * @Date 13:38 2020/4/29
 * @Author lql
 * @version 1.0
 **/
public class BYAuthException extends Exception
{
    private static final long serialVersionUID = -6916154462432027437L;

    public BYAuthException(String message)
    {
        super(message);
    }
}

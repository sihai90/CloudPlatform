package club.byteyun.common.exception;

/**
 * @ClassName ByteYunValidateCodeException
 * @Description //TODO 自定义验证码异常
 * @Date 17:37 2020/5/12
 * @Author lql
 * @version 1.0
 **/
public class ValidateCodeException extends Exception{

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message){
        super(message);
    }
}
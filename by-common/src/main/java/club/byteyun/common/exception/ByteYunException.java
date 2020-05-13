package club.byteyun.common.exception;

/**
 * @ClassName ByteYunException
 * @Description //TODO 自定义异常
 * @Date 12:06 2020/5/13
 * @Author lql
 * @version 1.0
 **/

public class ByteYunException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public ByteYunException(String message){
        super(message);
    }
}
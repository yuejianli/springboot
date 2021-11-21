package top.yueshushu.learn.mp.exception;

/**
 * @ClassName:BusinessException
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/10 12:07
 * @Version 1.0
 * @Since 1.0
 **/
public class BusinessException extends Exception {
    String message="";
    public BusinessException(String message){
        super(message);
    }
}

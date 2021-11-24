package top.yueshushu.learn.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.yueshushu.learn.response.OutputResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:MyExceptionHandler
 * @Description 自定义异常处理器
 * @Author zk_yjl
 * @Date 2021/11/10 10:37
 * @Version 1.0
 * @Since 1.0
 **/
@RestControllerAdvice
public class MyExceptionHandler {
    /**
     * 处理空指向的异常信息
     * @date 2021/11/10 11:52
     * @author zk_yjl
     * @param
     * @return top.yueshushu.learn.response.OutputResult
     */
    @ExceptionHandler(NullPointerException.class)
    public OutputResult npeException(HttpServletRequest req, NullPointerException e){
        return OutputResult.fail(e.getMessage());
    }

    /**
     * 处理算术的异常信息
     * @date 2021/11/10 11:52
     * @author zk_yjl
     * @param
     * @return top.yueshushu.learn.response.OutputResult
     */
    @ExceptionHandler(ArithmeticException.class)
    public OutputResult ariException(HttpServletRequest req, ArithmeticException  e){
        return OutputResult.fail(e.getMessage());
    }

    /**
     * 处理数组下标越界异常的异常信息
     * @date 2021/11/10 11:52
     * @author zk_yjl
     * @param
     * @return top.yueshushu.learn.response.OutputResult
     */
    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public OutputResult arrException(HttpServletRequest req, ArrayIndexOutOfBoundsException  e){
        return OutputResult.fail(e.getMessage());
    }

    /**
     * 处理自定义的业务异常的异常信息
     * @date 2021/11/10 11:52
     * @author zk_yjl
     * @param
     * @return top.yueshushu.learn.response.OutputResult
     */
    @ExceptionHandler(BusinessException.class)
    public OutputResult busException(HttpServletRequest req, BusinessException  e){
        return OutputResult.fail(e.getMessage());
    }


    /**
     * 处理其他的另外异常的异常信息
     * @date 2021/11/10 11:52
     * @author zk_yjl
     * @param
     * @return top.yueshushu.learn.response.OutputResult
     */
    @ExceptionHandler(Exception.class)
    public OutputResult otherException(HttpServletRequest req, Exception  e){
        return OutputResult.fail(e.getMessage());
    }
}

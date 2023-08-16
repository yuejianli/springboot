package top.yueshushu.learn.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.yueshushu.learn.pojo.OutputResult;
import top.yueshushu.learn.pojo.ResultCode;

import javax.validation.ConstraintViolationException;
import java.util.StringJoiner;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-04
 */

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public OutputResult handleMethodArgumentNotValidException(ConstraintViolationException ex) {
        return OutputResult.buildAlert(ResultCode.INVALID_PARAM, ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public OutputResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringJoiner joiner = new StringJoiner(",");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            joiner.add(fieldError.getField()).add(":").add(fieldError.getDefaultMessage());
        }
        String msg = joiner.toString();
        return OutputResult.buildAlert(ResultCode.INVALID_PARAM, msg);
    }
}

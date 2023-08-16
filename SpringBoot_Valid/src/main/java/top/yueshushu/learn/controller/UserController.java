package top.yueshushu.learn.controller;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.pojo.UserA;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @ClassName:RedisController
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/9 13:55
 * @Version 1.0
 * @Since 1.0
 **/
// @Validated
@RestController
public class UserController {


    @PostMapping("valid1")
    public String valid1(@RequestBody @Valid User user, BindingResult bindingResult) {
        // 如果有错误
        StringBuilder resultBuilder = new StringBuilder("");
        if (bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                resultBuilder.append(objectError.getCode() +"：" +objectError.getDefaultMessage()+",");
            }
            return resultBuilder.toString();
        }else {
            return "验证正确" +user.getName();
        }
//        StringBuilder resultBuilder = new StringBuilder("");
//        if (bindingResult.hasErrors()){
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            for (FieldError fieldError : fieldErrors) {
//                resultBuilder.append(fieldError.getField() +"：" +fieldError.getDefaultMessage()+",");
//            }
//            return resultBuilder.toString();
//        }else {
//            return "验证正确" +user.getName();
//        }
    }


    @PostMapping("valid2")
    public String valid1(@NotBlank(message = "姓名不能为空")
                             @Length(min = 2,max = 10, message = "姓名长度有误，在 2~10 之间") String name,
                         @NotNull(message = "年龄不能为空")
                         @Min(value = 18, message = "年龄最小是18") String age) {
      return name +"," +age;
    }

    @PostMapping("valid3")
    public String valid3(@RequestBody @Valid UserA userA, BindingResult bindingResult) {
        StringBuilder resultBuilder = new StringBuilder("");
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                resultBuilder.append(fieldError.getField() +"：" +fieldError.getDefaultMessage()+",");
            }
            return resultBuilder.toString();
        }else {
            return "验证正确" +userA.getName();
        }
    }


    @PostMapping("valid4")
    public String valid4(@RequestBody @Validated User user) {
        return "验证正确" +user.getName();
    }

    @PostMapping("valid5")
    public String valid5(@RequestBody @Valid User user) {
        return "验证正确" +user.getName();
    }
}

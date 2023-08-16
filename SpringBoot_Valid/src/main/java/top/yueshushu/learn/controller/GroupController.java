package top.yueshushu.learn.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.yueshushu.learn.group.GroupAdd;
import top.yueshushu.learn.group.GroupDelete;
import top.yueshushu.learn.group.GroupUpdate;
import top.yueshushu.learn.pojo.GroupUser;
import top.yueshushu.learn.pojo.User;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.List;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-04
 */
@RestController
@Slf4j
public class GroupController {

    @PostMapping("/addUser")
    public String addUser(@RequestBody  @Validated(value = {GroupAdd.class, Default.class}) GroupUser user, BindingResult bindingResult) {
        StringBuilder resultBuilder = new StringBuilder("");
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                resultBuilder.append(fieldError.getField() +"：" +fieldError.getDefaultMessage()+",");
            }
            return resultBuilder.toString();
        }
        log.info(">>> 可以进行添加操作");
        return "添加成功";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestBody  @Validated(value = {GroupUpdate.class, Default.class}) GroupUser user, BindingResult bindingResult) {
        StringBuilder resultBuilder = new StringBuilder("");
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                resultBuilder.append(fieldError.getField() +"：" +fieldError.getDefaultMessage()+",");
            }
            return resultBuilder.toString();
        }
        log.info(">>> 可以进行修改操作");
        return "修改成功";
    }
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestBody @Validated(value = {GroupDelete.class, Default.class}) GroupUser user, BindingResult bindingResult) {
        StringBuilder resultBuilder = new StringBuilder("");
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                resultBuilder.append(fieldError.getField() +"：" +fieldError.getDefaultMessage()+",");
            }
            return resultBuilder.toString();
        }
        log.info(">>> 可以进行删除操作");
        return "删除成功";
    }


    @PostMapping("/changePassword")
    public String changePassword(@RequestBody @Validated(value = {GroupUpdate.class, Default.class}) GroupUser user, BindingResult bindingResult) {
        StringBuilder resultBuilder = new StringBuilder("");
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                resultBuilder.append(fieldError.getField() +"：" +fieldError.getDefaultMessage()+",");
            }
            return resultBuilder.toString();
        }
        log.info(">>> 可以进行修改密码操作");
        return "修改密码成功";
    }
}

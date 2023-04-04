package top.yueshushu.learn.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @ClassName:User
 * @Description 用户实体
 * @Author 两个蝴蝶飞
 * @Date 2021/4/24 20:01
 * @Version 1.0
 **/
@Data
public class User implements Serializable {
    /**
     * @param id id编号
     * @param name 姓名
     * @param sex 性别
     * @param age 年龄
     * @param description 描述
     */
    private Integer id;
    @NotBlank(message = "姓名不能为空")
    @Length(min = 2,max = 10, message = "姓名长度有误，在 2~10 之间")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄最小是18")
    private Integer age;
    @Email(message = "邮箱格式错误")
    private String email;

    @Pattern(regexp = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$", message = "手机号格式错误")
    private String phone;

}



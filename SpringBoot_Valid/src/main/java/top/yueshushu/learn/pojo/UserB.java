package top.yueshushu.learn.pojo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-04
 */
@Data
public class UserB implements Serializable {
    @NotNull(message = "年龄不能为空")
    @Min(value = 18, message = "年龄最小是18")
    private Integer age;
    @Email(message = "邮箱格式错误")
    private String email;
}

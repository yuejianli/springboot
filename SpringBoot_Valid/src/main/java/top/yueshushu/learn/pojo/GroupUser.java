package top.yueshushu.learn.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.yueshushu.learn.group.GroupAdd;
import top.yueshushu.learn.group.GroupDelete;
import top.yueshushu.learn.group.GroupUpdate;
import top.yueshushu.learn.validator.Password;
import top.yueshushu.learn.validator.Sex;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * 分组的用户使用
 *
 * @author yuejianli
 * @date 2023-04-04
 */
@Data
public class GroupUser implements Serializable {

    // id 更新和删除时要填入
    @NotNull(message = "id不能为空", groups = {GroupUpdate.class, GroupDelete.class})
    private Integer id;
    @NotBlank(message = "姓名不能为空", groups = {GroupAdd.class})
    @Length(min = 2,max = 10, message = "姓名长度有误，在 2~10 之间")
    private String name;

    @Min(value = 18, message = "年龄最小是18", groups = {GroupUpdate.class, GroupDelete.class})
    private Integer age;


    // @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{6,9}$", message = "密码至少包含字母数字,6~9位",groups = {GroupUpdate.class})
    @Password(message = "使用验证注解的",groups = {GroupUpdate.class})
    private String password;

    @Sex(message = "性别错误")
    private String sex;
}

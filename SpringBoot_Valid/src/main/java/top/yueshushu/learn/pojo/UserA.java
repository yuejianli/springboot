package top.yueshushu.learn.pojo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-04
 */
@Data
public class UserA implements Serializable {
    @NotBlank(message = "姓名不能为空")
    @Length(min = 2,max = 10, message = "姓名长度有误，在 2~10 之间")
    private String name;

    @Valid
    @NotNull
    private UserB userB;
}

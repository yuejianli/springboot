package top.yueshushu.learn.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2023-04-04
 */
@Data
@ApiModel("用户响应对象")
public class UserVo implements Serializable {
    @ApiModelProperty(name = "name", value = "名称")
    private String name;
    @ApiModelProperty(name = "sex", value = "性别")
    private String sex;
    @ApiModelProperty(name = "age", value = "年龄")
    private Integer age;
}

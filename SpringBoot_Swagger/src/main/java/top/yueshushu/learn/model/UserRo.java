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
@ApiModel("用户请求对象")
public class UserRo implements Serializable {
    @ApiModelProperty(name = "name", value = "名称", example = "岳泽霖", required = true)
    private String name;
    @ApiModelProperty(name = "sex", value = "性别",notes = "男,女")
    private String sex;
    // 隐藏属性
    @ApiModelProperty(name = "age", value = "年龄", hidden = true)
    private Integer age;
}

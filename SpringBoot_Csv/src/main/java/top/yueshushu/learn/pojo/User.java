package top.yueshushu.learn.pojo;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

/**
 * @ClassName:User
 * @Description csv文件上传和下载
 * @Author zk_yjl
 * @Date 2021/11/5 9:32
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class User {
    @Alias("编号")
    private Integer id;
    @Alias("姓名")
    private String name;
 //   @Alias("性别")
    private String sex;
  //  @Alias("年龄")
    private Integer age;
    @Alias("描述信息")
    private String description;
}

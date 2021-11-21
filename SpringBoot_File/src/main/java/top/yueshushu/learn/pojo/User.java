package top.yueshushu.learn.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @ClassName:User
 * @Description Excel文件上传和下载
 * @Author zk_yjl
 * @Date 2021/11/5 9:32
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class User {
    @ExcelProperty(value="id编号")
    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("描述信息")
    private String description;
}

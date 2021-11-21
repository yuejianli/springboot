package top.yueshushu.learn.mp.dto;

import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import lombok.Data;
import top.yueshushu.learn.mp.ro.CodeGeneratorRequestRo;
import top.yueshushu.learn.mp.ro.DBConfigRo;
import top.yueshushu.learn.mp.ro.DBTableRo;
import top.yueshushu.learn.mp.ro.FileConfigRo;

import java.io.Serializable;

/**
 * @ClassName:CodeGeneratorDto
 * @Description 自动生成所需要的Dto,用于转换，接收数据。
 * @Author zk_yjl
 * @Date 2021/11/18 19:10
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class CodeGeneratorDto extends CodeGeneratorRequestRo implements Serializable {
    // 表名
    private String[] tableNamesArr;
    // 表前缀
    private String[] tablePrefixesArr;
    // 字段前缀
    private String[] fieldPrefixesArr;
    // 排出表的表名
    private String[] excludeTableNamesArr;
    // 忽略的字段
    private String[] ignoreColumnsArr;
}

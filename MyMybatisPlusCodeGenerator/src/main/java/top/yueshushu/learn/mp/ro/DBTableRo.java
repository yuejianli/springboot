package top.yueshushu.learn.mp.ro;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:DBTableRo
 * @Description 数据表的一些匹配信息
 * @Author zk_yjl
 * @Date 2021/11/17 17:51
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class DBTableRo implements Serializable {
    // 表名
    private String tableNames;
    // 表前缀
    private String tablePrefixes;
    // 字段前缀
    private String fieldPrefixes;
    // 排出表的表名
    private String excludeTableNames;
    // 忽略的字段
    private String ignoreColumns;

}

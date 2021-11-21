package top.yueshushu.learn.mp.ro;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:ProjectConfigRo
 * @Description 项目配置的一些信息
 * @Author zk_yjl
 * @Date 2021/11/20 16:57
 * @Version 1.0
 * @Since 1.0
 **/
@Data
public class ProjectConfigRo implements Serializable {
    /**
     xml文件里面是否用 baseMapper
     */
    private String baseMapper="0";
    /**
     xml文件里面是否用 baseColumn
     */
    private String baseColumn="0";
}

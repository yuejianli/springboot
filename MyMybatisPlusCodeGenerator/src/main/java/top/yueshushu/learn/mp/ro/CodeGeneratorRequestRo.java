package top.yueshushu.learn.mp.ro;

import lombok.Data;

/**
 * @since 2021-07-12
 */
@Data
public class CodeGeneratorRequestRo {
    // 作者
    private String author;

    private DBConfigRo  dbConfigRo;

    private DBTableRo  dbTableRo;

    private FileConfigRo fileConfigRo;

    private ProjectConfigRo projectConfigRo;
    /**
     * 自定义的sql语句
     */
    private String sql;
}

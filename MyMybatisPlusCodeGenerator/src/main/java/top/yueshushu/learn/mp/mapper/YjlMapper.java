package top.yueshushu.learn.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.mp.entity.YjlEntity;

import java.util.List;

/**
 * @ClassName:YjlMapper
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/11/21 17:56
 * @Version 1.0
 **/

public interface YjlMapper {
    /**
     * 查询该数据库所拥有的表
     *
     * @param dbName
     * @param excludeTables
     * @return
     */
    public List<String> selectTableNames(@Param("dbName") String dbName, @Param("excludeTables") List<String> excludeTables);

    /**
     * 删除表
     *
     * @param tableNames
     */
    void deleteTables(@Param("tableNames") List<String> tableNames);

    /**
     * 创建表
     *
     * @param ddlSql
     */
    void createTables(@Param("ddlSql") String ddlSql);
}

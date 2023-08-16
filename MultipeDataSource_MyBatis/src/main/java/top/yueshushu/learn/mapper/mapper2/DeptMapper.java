package top.yueshushu.learn.mapper.mapper2;

import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.two.Dept;

import java.util.List;

/**
 * @ClassName:DeptMapper
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/18 17:51
 * @Version 1.0
 **/
// @Mapper 不进行配置扫描
public interface DeptMapper {
    // 其他的方法. 具体使用可以参考 Mybatis 章节
    void addDept(@Param("dept") Dept dept);
    List<Dept> listDept();
}

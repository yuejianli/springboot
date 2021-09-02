package top.yueshushu.learn.mapper2;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.Dept;

import java.util.List;

/**
 * @ClassName:DeptMapper
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/18 17:51
 * @Version 1.0
 **/
@Mapper
public interface DeptMapper {
    void addDept(@Param("dept") Dept dept);
    List<Dept> listDept();
}

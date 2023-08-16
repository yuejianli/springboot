package top.yueshushu.learn.mapper.mapper2;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
//扫描由启动类进行扫描配置
public interface DeptMapper extends BaseMapper<Dept> {
    //走 xml 的配置
    void batchAdd(@Param("deptList") List<Dept> deptList);
}

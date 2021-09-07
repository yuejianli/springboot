package top.yueshushu.learn.mapper.mapper1;

import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.one.User;

import java.util.List;

/**
 * @ClassName:UserMapper
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/18 17:51
 * @Version 1.0
 **/
//@Mapper 不进行配置扫描
public interface UserMapper {
    // 其他的方法. 具体使用可以参考 Mybatis 章节
    void addUser(@Param("user") User user);
    List<User> listUser();
}

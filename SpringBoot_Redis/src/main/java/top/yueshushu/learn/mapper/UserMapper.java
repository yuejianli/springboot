package top.yueshushu.learn.mapper;

import org.apache.ibatis.annotations.Param;
import top.yueshushu.learn.pojo.User;

import java.util.List;

/**
 * @ClassName:UserMapper
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/18 17:51
 * @Version 1.0
 **/
public interface UserMapper {

    void addUser(@Param("user") User user);
    void deleteById(@Param("id") int id);
    User findById(@Param("id") int id);
    List<User> findAll();
}

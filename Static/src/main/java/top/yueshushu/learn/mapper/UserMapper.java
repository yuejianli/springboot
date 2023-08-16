package top.yueshushu.learn.mapper;

import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface UserMapper {

    void addUser(@Param("user") User user);

    void updateUser(@Param("user") User user);

    void deleteById(@Param("id") int id);

    void saveUser(@Param("user") User user);

    void batchAdd(@Param("userList") List<User> userList);

    void batchUpdate(@Param("userList") List<User> userList);

    void batchDeleteByIds(@Param("ids") List<Integer> ids);

    User findById(@Param("id") int id);

    List<User> findAll();

    List<User> findAllByIds(@Param("ids") List<Integer> ids);

    Long count();

    List<User> findByNameSexAndDesc(@Param("user") User user);

}

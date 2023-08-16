package top.yueshushu.learn.service;

import top.yueshushu.learn.pojo.User;

import java.util.List;

/**
 * @ClassName:UserService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
public interface UserService {

    User addUser(User user);
    User updateUser(User user);
    void deleteUser(int id);
    User findById(int id);
    List<User> findAll();
    /**
      演示 参数使用
     */
    List<User> findByNameAndSex(String name,String sex);
    /**
      演示  @Caching注解使用
     */
    List<User> findByNameAndSexAndId(String name,String sex,Integer id);
}

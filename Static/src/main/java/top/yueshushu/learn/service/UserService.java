package top.yueshushu.learn.service;

import com.github.pagehelper.PageInfo;
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

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(int id);

    void saveUser(User user);

    void batchAddUser(List<User> userList);

    void batchUpdateUser(List<User> userList);

    void batchDeleteByIds(List<Integer> ids);

    User findById(int id);

    List<User> findAll();

    List<User> findAllByIds(List<Integer> ids);

    Long count();

    List<User> findByNameSexAndDesc(User user);

    PageInfo pageUser(Integer pageNumber, Integer pageSize);


}

package top.yueshushu.learn.service;

import top.yueshushu.learn.pojo.User;

import java.util.List;
import java.util.Map;

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

    void deleteUser(Integer id);

    void batchAddUser(List<User> userList);

    User queryById(Integer id);

    List<User> queryForAll();

    Map<String, Object> queryForMapById(Integer id);

    Integer countAll();

    List<Integer> queryForIdList();

    void createTable();

    List<User> queryBeanForAll();

    List<User> queryRowSetForAll();

}

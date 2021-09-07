package top.yueshushu.learn.service;

import top.yueshushu.learn.pojo.one.User;

import java.util.List;

/**
 * @ClassName:UserService
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
public interface UserService {
    void batchAddUser(List<User> userList);
    List<User> listUser();
}

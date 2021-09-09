package top.yueshushu.learn.service;

import top.yueshushu.learn.pojo.Dept;
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

    List<User> listUser();

    void addDept(Dept dept);

    List<Dept> listDept();
}
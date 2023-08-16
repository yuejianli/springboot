package top.yueshushu.learn.service;

import top.yueshushu.learn.pojo.one.User;
import top.yueshushu.learn.pojo.two.Dept;

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
    //查询 springboot2 数据库的方法
    List<Dept> listDept();
}

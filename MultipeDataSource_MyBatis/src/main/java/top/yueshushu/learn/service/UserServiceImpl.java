package top.yueshushu.learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.mapper1.UserMapper;
import top.yueshushu.learn.pojo.User;

import java.util.List;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }
    @Override
    public List<User> listUser() {
       return userMapper.listUser();
    }
}

package top.yueshushu.learn.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.mapper.mapper1.UserMapper;
import top.yueshushu.learn.pojo.one.User;

import java.util.List;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
@DS("one")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void batchAddUser(List<User> userList) {
        userMapper.batchAdd(userList);
    }
    @Override
    public List<User> listUser() {
       return userMapper.selectList(null);
    }
}

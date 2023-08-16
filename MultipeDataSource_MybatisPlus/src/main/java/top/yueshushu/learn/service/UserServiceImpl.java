package top.yueshushu.learn.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.yueshushu.learn.mapper.mapper1.UserMapper;
import top.yueshushu.learn.mapper.mapper2.DeptMapper;
import top.yueshushu.learn.pojo.one.User;
import top.yueshushu.learn.pojo.two.Dept;

import java.util.List;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
//在类上添加注解，表示类中所有的方法都走这个数据源。
@DS("one")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void batchAddUser(List<User> userList) {
        userMapper.batchAdd(userList);
    }
    @Override
    public List<User> listUser() {
       return userMapper.selectList(null);
    }
    //在方法上添加注解，表示这个方法走定义的数据源
    @DS("two")
    @Override
    public List<Dept> listDept() {
        return deptMapper.selectList(null);
    }
}

package top.yueshushu.learn.service;

import org.springframework.stereotype.Service;
import top.yueshushu.learn.pojo.two.Dept;
import top.yueshushu.learn.pojo.one.User;
import top.yueshushu.learn.repository.two.DeptRepository;
import top.yueshushu.learn.repository.one.UserRepository;

import javax.annotation.Resource;
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
    // 数据源1  放置到 springboot 数据库里面
    @Resource
    private UserRepository userRepository;
    //数据源2， 放置到 springboot2 数据库里面
    @Resource
    private DeptRepository deptRepository;

    /**
     * 使用的是数据源 springboot
     */
    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }
    /**
     * 使用的是数据源 springboot
     */
    @Override
    public List<User> listUser() {
       return userRepository.findAll();
    }
    /**
     * 使用的是数据源 springboot2
     */
    @Override
    public void addDept(Dept dept) {
      deptRepository.save(dept);
    }
    /**
     * 使用的是数据源 springboot2
     */
    @Override
    public List<Dept> listDept() {
       return deptRepository.findAll();
    }
}

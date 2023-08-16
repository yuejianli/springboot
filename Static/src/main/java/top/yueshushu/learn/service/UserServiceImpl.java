package top.yueshushu.learn.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yueshushu.learn.mapper.UserMapper;
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
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public void batchAddUser(List<User> userList) {
        userMapper.batchAdd(userList);
    }

    @Override
    public void batchUpdateUser(List<User> userList) {
        userMapper.batchUpdate(userList);
    }

    @Override
    public void batchDeleteByIds(List<Integer> ids) {
        userMapper.batchDeleteByIds(ids);
    }

    @Override
    public User findById(int id) {
        return userMapper.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findAllByIds(List<Integer> ids) {
        return userMapper.findAllByIds(ids);
    }

    @Override
    public Long count() {
        return userMapper.count();
    }

    @Override
    public List<User> findByNameSexAndDesc(User user) {
        return userMapper.findByNameSexAndDesc(user);
    }

    @Override
    public PageInfo pageUser(Integer pageNumber, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNumber,pageSize);
        //正常的查询
        List<User> userList=userMapper.findAll();
        //将结果转换成PageInfo对象。
        PageInfo pageInfo=new PageInfo(userList);
        return pageInfo;
    }
}

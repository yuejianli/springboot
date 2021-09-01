package top.yueshushu.learn.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteById(id);
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
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
        return userMapper.selectById(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public List<User> findAllByIds(List<Integer> ids) {
        return userMapper.selectBatchIds(ids);
    }

    @Override
    public Long count() {
        return Long.valueOf(userMapper.selectCount(null));
    }

    @Override
    public List<User> findByNameSexAndDesc(User user) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        queryWrapper.like(!StringUtils.isEmpty(user.getName()),"name",user.getName());
        queryWrapper.eq(!StringUtils.isEmpty(user.getSex()),"sex",user.getSex());
        queryWrapper.eq(!StringUtils.isEmpty(user.getDescription()),"description",
                user.getDescription());
        return userMapper.selectList(queryWrapper);
    }

    @Override
    public Page pageUser(Integer pageNumber, Integer pageSize) {
        //开启分页
        Page userPage=new Page(pageNumber,pageSize);
        //正常的查询
        userMapper.selectPage(userPage,null);
        return userPage;
    }


    @Override
    public PageInfo githubPageUser(Integer pageNumber, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNumber,pageSize);
        //正常的查询
        List<User> userList=userMapper.selectList(null);
        //将结果转换成PageInfo对象。
        PageInfo pageInfo=new PageInfo(userList);
        return pageInfo;
    }
}

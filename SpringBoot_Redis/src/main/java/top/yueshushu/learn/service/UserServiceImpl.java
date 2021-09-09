package top.yueshushu.learn.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.yueshushu.learn.mapper.UserMapper;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.util.RedisUtil;

import java.util.List;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/24 20:05
 * @Version 1.0
 **/
@Service
@Log4j2
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    private static final String KEY_PRE="user_";
    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
        log.info("将用户编号为{}放置到缓存里面",user.getId());
        redisUtil.set(KEY_PRE+user.getId(),user);

    }
    @Override
    public void deleteUser(int id) {
        userMapper.deleteById(id);
        log.info("将用户编号为{}从缓存中移除",id);
        redisUtil.delByKey(KEY_PRE+id);
    }
    @Override
    public User findById(int id) {
        log.info("先从缓存中查询用户编号为{} 是否存在",id);
        User user=redisUtil.get(KEY_PRE+id);
        if(user!=null){
            log.info(">>>>>>>>>>使用的是缓存中的数据");
            return user;
        }
        log.info(">>>>>>>>>>>从数据库中查询，并放置到缓存中");
        user= userMapper.findById(id);
        redisUtil.set(KEY_PRE+id,user);
        return user;
    }
    @Override
    public List<User> findAll() {
        log.info("先从缓存中查询用户列表是否存在");
        List<User> userList= (List<User>) redisUtil.range(KEY_PRE+"ALL");
        if(!CollectionUtils.isEmpty(userList)){
            log.info(">>>>>>>>>>使用的是缓存中的数据");
            return userList;
        }
        log.info(">>>>>>>>>>>从数据库中查询，并放置到缓存中");
        userList= userMapper.findAll();
        redisUtil.leftPushAll(KEY_PRE+"ALL",userList);
        return userList;
    }
}

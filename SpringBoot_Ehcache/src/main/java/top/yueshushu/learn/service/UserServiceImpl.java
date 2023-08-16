package top.yueshushu.learn.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
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
@Log4j2
@CacheConfig(cacheNames ={"user_"})
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @CachePut(key = "#result.id")
    @Override
    public User addUser(User user) {
        userMapper.addUser(user);
        return user;

    }
    @Override
    @CachePut(key = "#user.id")
    public User updateUser(User user) {
        userMapper.updateUser(user);
        //更新全部的缓存信息
        return user;
    }

    @Override
    @CacheEvict(key = "#id",allEntries = true)
    public void deleteUser(int id) {
        userMapper.deleteById(id);
    }
    @Override
    @Cacheable(key="#id" )
    public User findById(int id) {
        return userMapper.findById(id);
    }
    @Override
    @Cacheable(key = "#root.targetClass+#root.methodName")
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Cacheable(key = "#root.targetClass+#root.methodName")
    @Override
    public List<User> findByNameAndSex(String name, String sex) {
        return userMapper.findByNameAndSex(name,sex);
    }
    @Caching(
            cacheable = {
                    @Cacheable(key = "#name"),  // 放置缓存到 name
                    @Cacheable(key="#sex")     // 放置缓存到 sex ， id的缓存用  put更新
            },
            put = {
                    @CachePut(key="#id")    //同时更新 id缓存
            }
    )
    @Override
    public List<User> findByNameAndSexAndId(String name, String sex, Integer id) {
        return userMapper.findByNameAndSexAndId(name,sex,id);
    }
}

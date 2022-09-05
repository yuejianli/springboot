package top.yueshushu.learn.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import top.yueshushu.learn.mapper.UserMapper;
import top.yueshushu.learn.pojo.User;

/**
 * 用途描述
 *
 * @author yuejianli
 * @date 2022-09-05
 */
@Slf4j
@Service("userMoreService")
public class UserMoreServiceImpl extends ServiceImpl<UserMapper, User> implements UserMoreService{

}

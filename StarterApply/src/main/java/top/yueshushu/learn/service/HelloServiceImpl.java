package top.yueshushu.learn.service;

import org.springframework.stereotype.Service;

/**
 * @ClassName:HelloServiceImpl
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 13:45
 * @Version 1.0
 * @Since 1.0
 **/
@Service
public class HelloServiceImpl implements HelloService {
   // @MyLog
    @Override
    public int add(int a, int b) {
       return a+b;
    }
}

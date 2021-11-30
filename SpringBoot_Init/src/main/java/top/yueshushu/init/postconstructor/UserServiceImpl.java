package top.yueshushu.init.postconstructor;

import org.springframework.stereotype.Service;

/**
 * @ClassName:UserServiceImpl
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/30 17:00
 * @Version 1.0
 * @Since 1.0
 **/
@Service
public class UserServiceImpl implements UserService {


    @Override
    public void set() {
        System.out.println("UserService--->set值");
    }

    @Override
    public void get() {
        System.out.println("UserService--->取出值");
    }
}

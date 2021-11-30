package top.yueshushu.init.postconstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName:InitMethod
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/30 14:09
 * @Version 1.0
 * @Since 1.0
 **/
//@Component
//@Order(4)
public class InitMethod {
    @Autowired
    private UserService userService;
    @PostConstruct
    public void initName(){
        System.out.println(">>>>初始化名称模块");
    }
    @PostConstruct
    public void initSet(){
        userService.set();
    }
    @PostConstruct
    public void initGet(){
        userService.get();
    }
}

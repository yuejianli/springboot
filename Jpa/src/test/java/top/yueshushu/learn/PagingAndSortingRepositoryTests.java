package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.service.UserService;

import java.util.List;

/**
 * @ClassName:PagingAndSortingRepositoryTests
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/26 20:00
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class PagingAndSortingRepositoryTests {
    @Autowired
    private UserService userService;
    @Test
    public void sortTest(){
        List<User> userList=userService.findAllOrderBySexAndAge();
        userList.forEach(n->log.info(n));
    }
    @Test
    public void pageTest(){
        Page<User> page=userService.pageAll();
        log.info("总页数:{}",page.getTotalPages());
        log.info("总的数目:{}",page.getTotalElements());
        log.info("当前页数:{}",page.getNumber()+1);
        log.info("当前页的数目:{}",page.getNumberOfElements());
        List<User> userList= page.getContent();
        userList.forEach(n->log.info(n));
    }

}

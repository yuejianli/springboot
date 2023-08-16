package top.yueshushu.init.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.init.postconstructor.MyUserServiceUtil;
import top.yueshushu.init.postconstructor.MyUserServiceUtil2;

/**
 * @ClassName:PostTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/30 17:12
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootTest
public class PostTest {
  //先注入
  @Autowired
  private MyUserServiceUtil myUserServiceUtil;
    @Test
    public void test(){
        System.out.println(">>>先注入，再使用");
        //再使用
        myUserServiceUtil.handler();
    }


    @Test
    public void test2(){
        System.out.println("静态方法，直接使用");
        MyUserServiceUtil2.handler();
    }
}

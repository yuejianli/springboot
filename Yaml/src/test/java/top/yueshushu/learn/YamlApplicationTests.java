package top.yueshushu.learn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.pojo.Book;
import top.yueshushu.learn.pojo.SingleInfo;
import top.yueshushu.learn.pojo.SingleInfo2;

/**
 * @ClassName:YamlApplicationTests
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/10 12:02
 * @Version 1.0
 **/
@SpringBootTest
class YamlApplicationTests {
    @Autowired
    private SingleInfo singleInfo;
    @Autowired
    private Book book;
    @Autowired
    private SingleInfo2 singleInfo2;
    @Test
    void contextLoads() {
        System.out.println(singleInfo);
       // System.out.println(book);
      //  System.out.println(singleInfo2);
    }
}

package top.yueshushu.learn.relation;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.yueshushu.learn.service.StudentService;

/**
 * @ClassName:StudentOneToOneTests
 * @Description TODO
 * @Author yjl
 * @Date 2021/5/14 16:11
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class StudentOneToOneTests {
    @Autowired
    private StudentService studentService;
    @Test
    public void oneToOneInsertTest(){
        studentService.saveInfo();
    }
    @Test
    public void queryTest(){
        studentService.queryInfo();
    }
    @Test
    public void teacherSaveManyTest(){
        studentService.teacherSaveManyInfo();
    }
    @Test
    public void teacherQueryTest(){
        studentService.teacherQueryInfo();
    }

}

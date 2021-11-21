package top.yueshushu.learn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import top.yueshushu.condition.Animal;
import top.yueshushu.condition.JavaAnimalConfig;
import top.yueshushu.starter.service.UserPropertiesService;

/**
 * @ClassName:StarterTest
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/22 17:26
 * @Version 1.0
 * @Since 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StarterTest {

    @Autowired
    private UserPropertiesService userPropertiesService;
    @Test
    public void starterPrintlnTest(){
        String message = userPropertiesService.println();
        System.out.println("Apply项目输出信息:"+message);
    }

    @Test
    public void animalJavaTes(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //ctx.getEnvironment().getSystemProperties().put("animal", "Cat");
        // ctx.getEnvironment().getSystemProperties().put("animal", "Dog");
        // ctx.getEnvironment().getSystemProperties().put("animal", "YJL");

         ctx.getEnvironment().setActiveProfiles("Dog");
        //ctx.getEnvironment().setActiveProfiles("Cat");
        // ctx.getEnvironment().setActiveProfiles("YJL");

        ctx.register(JavaAnimalConfig.class);
        ctx.refresh();
        Animal animal = (Animal) ctx.getBean("animal");
        animal.voice();
    }
}

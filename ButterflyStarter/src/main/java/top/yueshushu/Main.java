package top.yueshushu;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.yueshushu.condition.Animal;
import top.yueshushu.condition.JavaAnimalConfig;

/**
 * @ClassName:Main
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 21:01
 * @Version 1.0
 * @Since 1.0
 **/
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //ctx.getEnvironment().getSystemProperties().put("animal", "Cat");
       // ctx.getEnvironment().getSystemProperties().put("animal", "Dog");
       // ctx.getEnvironment().getSystemProperties().put("animal", "YJL");


        ctx.getEnvironment().setActiveProfiles("Dog");
       // ctx.getEnvironment().setActiveProfiles("Cat");
        //ctx.getEnvironment().setActiveProfiles("YJL");

        ctx.register(JavaAnimalConfig.class);
        ctx.refresh();
        Animal animal = (Animal) ctx.getBean("animal");
        animal.voice();
    }
}
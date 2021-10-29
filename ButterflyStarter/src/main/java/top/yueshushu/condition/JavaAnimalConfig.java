package top.yueshushu.condition;

import org.springframework.context.annotation.*;

/**
 * @ClassName:JavaAnimalConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 20:54
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class JavaAnimalConfig {
    /**
    条件符合时，创建
     */
    @Bean("animal")
  //  @Conditional(DogCondition.class)  不使用
    @Profile("Dog") //触发条件
    Animal dog(){
        return new Dog();
    }
    /**
     条件符合时，创建
     */
    @Bean("animal")
    //@Conditional(CatCondition.class)
    @Profile("Cat") //触发条件
    Animal cat(){
        return new Cat();
    }
}

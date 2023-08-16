package top.yueshushu.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @ClassName:DogCondition
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 20:51
 * @Version 1.0
 * @Since 1.0
 **/
public class DogCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String animal = context.getEnvironment().getProperty("animal");
        if(StringUtils.isEmpty(animal)){
            return true;
        }
        return "Dog".equalsIgnoreCase(animal);
    }
}

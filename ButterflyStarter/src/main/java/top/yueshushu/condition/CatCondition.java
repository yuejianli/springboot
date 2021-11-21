package top.yueshushu.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @ClassName:CatCondition
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/10/25 20:53
 * @Version 1.0
 * @Since 1.0
 **/
// 实现 Condition 接口
public class CatCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String animal = context.getEnvironment().getProperty("animal");
        //为空属性，不创建
        if(StringUtils.isEmpty(animal)){
            return false;
        }
        return "Cat".equalsIgnoreCase(animal);
    }
}

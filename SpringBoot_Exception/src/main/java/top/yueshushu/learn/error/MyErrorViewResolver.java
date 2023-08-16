package top.yueshushu.learn.error;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.sql.DataSourceDefinition;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:MyErrorViewResolver
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/11/24 17:40
 * @Version 1.0
 * @Since 1.0
 **/
//@Component
public class MyErrorViewResolver extends DefaultErrorViewResolver {
   /**
    构造方法
    */
    public MyErrorViewResolver(ApplicationContext applicationContext, ResourceProperties resourceProperties) {
        super(applicationContext, resourceProperties);
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        // 可以根据状态  status, 自定义视图页面和 model
        //不能直接修改 model
        Map<String, Object> newMap = new HashMap<>();
        for(Map.Entry<String,Object> entry:model.entrySet()){
            newMap.put(entry.getKey(),entry.getValue());
        }
        newMap.put("findUser","两个蝴蝶飞");
       // NOT_FOUND(404, "Not Found"),
        if(HttpStatus.NOT_FOUND.equals(status)){
            return new ModelAndView("/self/404.html",newMap);
        }
        // 500错误
       // INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
        if(HttpStatus.INTERNAL_SERVER_ERROR.equals(status)){
            return new ModelAndView("/self/500.html",newMap);
        }
        //走一个默认的页面
        return new ModelAndView("/static/error/500.html",newMap);
    }
}

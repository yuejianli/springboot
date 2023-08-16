package top.yueshushu.learn.error;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @ClassName:MyErrorAttributes
 * @Description 后端自定义的提示信息
 * @Author zk_yjl
 * @Date 2021/11/24 17:34
 * @Version 1.0
 * @Since 1.0
 **/
//@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        //也可以放置其他的属性信息.
        if ((Integer)map.get("status") == 500) {
            map.put("message", "服务器内部错误!");
        }
        if ((Integer)map.get("status") == 404) {
            map.put("message", "页面找不到!");
        }
        if ((Integer)map.get("status") == 403) {
            map.put("message", "未授权!");
        }
        return map;
    }
}

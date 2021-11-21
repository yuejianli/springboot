package top.yueshushu.learn.util;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName:BeanUtil
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/22 15:18
 * @Version 1.0
 * @Since 1.0
 **/
public class BeanConvertUtil {
    /**
     * 将对象转换成普通的字符串
     * @date 2021/9/16 14:17
     * @author zk_yjl
     * @param value
     * @return java.lang.String
     */
    public static <T> String beanToString(T value) {
        if (value==null)
            return null;
        Class<?> aClass = value.getClass();
        if (aClass==int.class||aClass==Integer.class){
            return ""+value;
        }else if (aClass==String.class){
            return (String) value;
        }else if (aClass==long.class||aClass==Long.class){
            return ""+value;
        }else {
            return JSONObject.toJSONString(value);
        }
    }
    /**
     * 将字符串转换成相应的对象
     * @date 2021/9/16 14:17
     * @author zk_yjl
     * @param str
     * @param aClass
     * @return T
     */
    public static <T> T stringToBean(String str,Class<T> aClass) {
        if (str==null||str.length()<=0||aClass==null){
            return null;
        }
        if (aClass==int.class||aClass==Integer.class){
            return (T)Integer.valueOf(str);
        }else if (aClass==String.class){
            return (T)str;
        }else if (aClass==long.class||aClass==Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSONObject.parseObject(str,aClass);
        }
    }
}

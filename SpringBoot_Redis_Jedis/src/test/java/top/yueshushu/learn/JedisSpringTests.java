package top.yueshushu.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import top.yueshushu.learn.pojo.User;

/**
 * @ClassName:JedisSpringTests
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/16 14:05
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class JedisSpringTests {
    @Autowired
    private JedisPool jedisPool;
    /**
     * 只用String 进行举例。
     */
    @Test
    public void stringTest(){
        //1. 放置普通的字符串信息
        String name="岳泽霖";
        Jedis jedis=jedisPool.getResource();
        //设置数据库
        jedis.select(15);
        jedis.set("jname",name);
        String getName=jedis.get("jname");
        log.info("Jedis获取信息:"+getName);
    }
    @Test
    public void beanTest(){
        //1. 放置JavaBean 信息
        User user=new User();
        user.setId(1);
        user.setName("岳泽霖");
        user.setSex("男");
        user.setAge(26);
        user.setDescription("一个快乐的程序员");
        Jedis jedis=jedisPool.getResource();
        //设置数据库
        jedis.select(15);
        jedis.set("bean信息",beanToString(user));
        //获取信息
        User rUser=stringToBean(jedis.get("bean信息"),User.class);
        log.info("Jedis获取用户信息:>>>"+rUser.toString());
    }
    /**
     * 将对象转换成普通的字符串
     * @date 2021/9/16 14:17
     * @author zk_yjl
     * @param value
     * @return java.lang.String
     */
    private <T> String beanToString(T value) {
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
    private <T> T stringToBean(String str,Class<T> aClass) {
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

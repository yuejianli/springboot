package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.yueshushu.learn.pojo.User;

/**
 * @ClassName:RedisTests
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/9 14:04
 * @Version 1.0
 * @Since 1.0
 **/
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Log4j2
public class RedisTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 只用String 进行举例。
     */
    @Test
    public void stringTest(){
        //1. 放置普通的字符串信息
        String name="岳泽霖";
        redisTemplate.opsForValue().set("name",name);
        String getName=(String)redisTemplate.opsForValue().get("name");
        log.info("RedisTemplate获取信息:"+getName);
        stringRedisTemplate.opsForValue().set("sname",name);
        String sGetName=stringRedisTemplate.opsForValue().get("sname");
        log.info("StringRedisTemplate 获取信息:>>>"+sGetName);
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
      //  redisTemplate.opsForValue().set("bean",user);
        //获取信息
        User rUser=(User)redisTemplate.opsForValue().get("user_::45欢欢");
        log.info("RedisTemplate获取用户信息:>>>"+rUser.toString());

        //StringRedisTemplate 放置对象 user, 不太好放置。

    }
}

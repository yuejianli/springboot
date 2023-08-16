package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@Slf4j
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
}

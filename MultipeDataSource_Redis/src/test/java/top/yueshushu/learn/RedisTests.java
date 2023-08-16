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
import top.yueshushu.learn.util.RedisUtil;

import javax.annotation.Resource;

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

    @Resource(name = "masterRedisTemplate")
    private RedisTemplate<String, Object> masterRedisTemplate;

    @Resource(name = "slaveRedisTemplate")
    private RedisTemplate<String, Object> slaveRedisTemplate;

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void setAndGetValueTest() {
        // 设置信息
        masterRedisTemplate.opsForValue().set("name", "主Redis数据");
        slaveRedisTemplate.opsForValue().set("name", "从Redis数据");

        // 获取主数据
        log.info(">>> 获取主数据 {}", masterRedisTemplate.opsForValue().get("name"));
        log.info(">>> 获取从数据 {}", slaveRedisTemplate.opsForValue().get("name"));

    }

    @Test
    public void swaggerTest() {
        redisUtil.set("name2", "主Redis数据信息");

        //进行调整，调整到 从
        redisUtil.chooseSlave();

        redisUtil.set("name2", "从 Redis数据信息");

        // 获取主信息

        redisUtil.chooseMaster();


        log.info(">>> 获取主数据 {}", redisUtil.get("name2").toString());

        redisUtil.chooseSlave();

        log.info(">>> 获取从数据 {}", redisUtil.get("name2").toString());


    }

}

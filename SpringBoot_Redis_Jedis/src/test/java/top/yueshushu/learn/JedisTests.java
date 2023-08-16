package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolAbstract;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName:JedisTests
 * @Description Jedis的基础操作
 * @Author zk_yjl
 * @Date 2021/9/16 13:45
 * @Version 1.0
 * @Since 1.0
 **/
@Log4j2
public class JedisTests {

    /**
     * Jedis直接连接
     * @date 2021/9/16 13:48
     * @author zk_yjl
     * @param
     * @return void
     */
    @Test
    public void jedisConnectionTest(){
        // 指定 ip和端口号
        Jedis jedis=new Jedis("127.0.0.1", 6379,3000);
        //输入密码
        jedis.auth("zk123");
        log.info("是否连接成功:"+jedis.ping());
        //指定哪一个数据库
        jedis.select(15);
        //设置信息
        jedis.set("jedis","我是Jedis直接连接的信息");
        log.info("输出值:"+jedis.get("jedis"));
        jedis.close();
    }
    @Test
    public void jedisPoolTest(){
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(30);  // 设置连接池的最大连接数
        jpc.setMaxIdle(8);    // 设置连接池允许的最大空闲连接数
        // 初始化连接池类（使用自定义连接池参数）
        JedisPool jp = new JedisPool(jpc, "localhost", 6379,3000);
        Jedis jedis=jp.getResource();
        //输入密码
        jedis.auth("zk123");
        log.info("是否连接成功:"+jedis.ping());
        //指定数据库
        jedis.select(15);
        //设置信息
        jedis.set("jedisC","我是Jedis通过连接池连接的信息");
        log.info("输出值:"+jedis.get("jedisC"));
    }

    @Test
    public void chinaKeyTest(){
        JedisPoolConfig jpc = new JedisPoolConfig();
        jpc.setMaxTotal(30);  // 设置连接池的最大连接数
        jpc.setMaxIdle(8);    // 设置连接池允许的最大空闲连接数
        // 初始化连接池类（使用自定义连接池参数）
        JedisPool jp = new JedisPool(jpc, "localhost", 6379,3000);
        Jedis jedis=jp.getResource();
        //输入密码
        jedis.auth("zk123");
        log.info("是否连接成功:"+jedis.ping());
        //指定数据库
        jedis.select(15);
        //设置信息
        jedis.set("作者名字","两个蝴蝶飞");
        log.info("输出值:"+jedis.get("作者名字"));
    }
}

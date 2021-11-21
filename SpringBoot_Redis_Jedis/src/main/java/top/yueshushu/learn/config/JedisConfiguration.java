package top.yueshushu.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName:JedisConfiguration
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/16 12:28
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class JedisConfiguration {
    /**
        这些属性并不会自动注入
     */
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;
   /**
    * 构建 JedisPoolConfig 对象，用于填充属性。
    * @date 2021/9/16 13:44
    * @author zk_yjl
    * @param
    * @return redis.clients.jedis.JedisPoolConfig
    */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        /*最大空闲数*/
        config.setMaxIdle(maxIdle);
        /*连接池的最大数据库连接数*/
        config.setMaxTotal(10000);
        /*最大建立连接等待时间*/
        config.setMaxWaitMillis(maxWait);
        /*是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个*/
        config.setTestOnBorrow(true);
        /*在空闲时检查有效性, 默认false*/
        config.setTestWhileIdle(false);
        return config;
    }
    /**
     * 构建JedisPool
     * @date 2021/9/16 13:45
     * @author zk_yjl
     * @param
     * @return redis.clients.jedis.JedisPool
     */
    @Bean
    public JedisPool jedisPoolFactory() {
        JedisPool jedisPool;
        if (StringUtils.isEmpty(password)) {
            jedisPool = new JedisPool(jedisPoolConfig(), host, port, timeout);
        } else {
            jedisPool = new JedisPool(jedisPoolConfig(), host, port, timeout, password);
        }
        return jedisPool;
    }
}

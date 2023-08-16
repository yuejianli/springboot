package top.yueshushu.learn.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * @ClassName:RedisConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/9 14:24
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    // 第一个数据库配置信息
    @Value("${spring.masterRedis.host}")
    private String masterRedisHost;
    @Value("${spring.masterRedis.database}")
    private Integer masterRedisDataBase;
    @Value("${spring.masterRedis.port}")
    private Integer masterRedisPort;
    @Value("${spring.masterRedis.password}")
    private String masterRedisPassword;
    @Value("${spring.masterRedis.timeout}")
    private Integer masterRedisTimeout;
    @Value("${spring.masterRedis.lettuce.pool.max-active}")
    private Integer masterRedisMaxActive;
    @Value("${spring.masterRedis.lettuce.pool.min-idle}")
    private Integer masterRedisMinIdle;
    @Value("${spring.masterRedis.lettuce.pool.max-wait}")
    private Integer masterRedisMaxWait;


    /**
     * 第二个数据库配置信息
     */
    @Value("${spring.slaveRedis.host}")
    private String slaveRedisHost;
    @Value("${spring.slaveRedis.database}")
    private Integer slaveRedisDataBase;
    @Value("${spring.slaveRedis.port}")
    private Integer slaveRedisPort;
    @Value("${spring.slaveRedis.password}")
    private String slaveRedisPassword;
    @Value("${spring.slaveRedis.timeout}")
    private Integer slaveRedisTimeout;
    @Value("${spring.slaveRedis.lettuce.pool.max-active}")
    private Integer slaveRedisMaxActive;
    @Value("${spring.slaveRedis.lettuce.pool.min-idle}")
    private Integer slaveRedisMinIdle;
    @Value("${spring.slaveRedis.lettuce.pool.max-wait}")
    private Integer slaveRedisMaxWait;


    @Bean(name = "masterPool")
    public GenericObjectPoolConfig getMasterPoolConfig() {
        // 配置redis连接池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(masterRedisMaxActive);
        poolConfig.setMaxIdle(masterRedisMaxActive);
        poolConfig.setMinIdle(masterRedisMinIdle);
        poolConfig.setMaxWaitMillis(masterRedisMaxWait);
        return poolConfig;
    }

    @Bean(name = "slavePool")
    public GenericObjectPoolConfig getSlavePoolConfig() {
        // 配置redis连接池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(slaveRedisMaxActive);
        poolConfig.setMaxIdle(slaveRedisMaxActive);
        poolConfig.setMinIdle(slaveRedisMinIdle);
        poolConfig.setMaxWaitMillis(slaveRedisMaxWait);
        return poolConfig;
    }

    @Bean(name = "masterRedisTemplate")
    public RedisTemplate<String, Object> getMasterRedisTemplate() {
        // 构建工厂对象
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(masterRedisHost);
        config.setPort(masterRedisPort);
        config.setPassword(RedisPassword.of(masterRedisPassword));
        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(masterRedisTimeout))
                .poolConfig(getMasterPoolConfig())
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        // 设置使用的redis数据库
        factory.setDatabase(masterRedisDataBase);
        // 重新初始化工厂
        factory.afterPropertiesSet();
        return redisTemplate(factory);
    }

    @Bean(name = "slaveRedisTemplate")
    public RedisTemplate<String, Object> getSlaveRedisTemplate() {
        // 构建工厂对象
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(slaveRedisHost);
        config.setPort(slaveRedisPort);
        config.setPassword(RedisPassword.of(slaveRedisPassword));
        LettucePoolingClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(slaveRedisTimeout))
                .poolConfig(getSlavePoolConfig())
                .build();
        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        // 设置使用的redis数据库
        factory.setDatabase(slaveRedisDataBase);
        // 重新初始化工厂
        factory.afterPropertiesSet();
        return redisTemplate(factory);
    }

    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式

        template.setKeySerializer(new StringRedisSerializer());

        // hash的key也采用String的序列化方式

        template.setHashKeySerializer(stringRedisSerializer);

        template.setValueSerializer(jackson2JsonRedisSerializer);

        // hash的value序列化方式采用jackson

        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        template.afterPropertiesSet();

        return template;

    }
}

配置多数据源 Redis 信息

# 配置文件

application.yml

~~~yaml
# 引入 数据库的相关配置
spring:
  # 配置Redis的使用
  masterRedis:
    database: 14 # 所使用的数据库  默认是0
    host: 127.0.0.1  #所使用的redis的主机地址
    port: 6379  # 端口号  默认是 6379
    password: zk123 # 密码
    timeout: 5000 # 超时时间  5000毫秒
    # 连接池 lettuce 的配置
    lettuce:
      pool:
        max-active: 100
        min-idle: 10
        max-wait: 100000
  # 配置Redis的使用
  slaveRedis:
    database: 15 # 所使用的数据库  默认是0
    host: 127.0.0.1  #所使用的redis的主机地址
    port: 6379  # 端口号  默认是 6379
    password: zk123 # 密码
    timeout: 5000 # 超时时间  5000毫秒
    # 连接池 lettuce 的配置
    lettuce:
      pool:
        max-active: 100
        min-idle: 10
        max-wait: 100000
~~~

# 配置 Config

~~~java

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
     第二个数据库配置信息
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



    @Bean(name="masterPool")
    public GenericObjectPoolConfig getMasterPoolConfig(){
        // 配置redis连接池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(masterRedisMaxActive);
        poolConfig.setMaxIdle(masterRedisMaxActive);
        poolConfig.setMinIdle(masterRedisMinIdle);
        poolConfig.setMaxWaitMillis(masterRedisMaxWait);
        return poolConfig;
    }

    @Bean(name="slavePool")
    public GenericObjectPoolConfig getSlavePoolConfig(){
        // 配置redis连接池
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(slaveRedisMaxActive);
        poolConfig.setMaxIdle(slaveRedisMaxActive);
        poolConfig.setMinIdle(slaveRedisMinIdle);
        poolConfig.setMaxWaitMillis(slaveRedisMaxWait);
        return poolConfig;
    }

    @Bean(name = "masterRedisTemplate")
    public RedisTemplate<String, Object> getMasterRedisTemplate(){
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
        return  redisTemplate(factory);
    }

    @Bean(name = "slaveRedisTemplate")
    public RedisTemplate<String, Object> getSlaveRedisTemplate(){
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
        return  redisTemplate(factory);
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
~~~

# 测试和 切换

~~~java

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
        masterRedisTemplate.opsForValue().set("name","主Redis数据");
        slaveRedisTemplate.opsForValue().set("name","从Redis数据");

        // 获取主数据
        log.info(">>> 获取主数据 {}",masterRedisTemplate.opsForValue().get("name"));
        log.info(">>> 获取从数据 {}",slaveRedisTemplate.opsForValue().get("name"));

    }

    @Test
    public void swaggerTest(){
        redisUtil.set("name2","主Redis数据信息");

        //进行调整，调整到 从
        redisUtil.chooseSlave();

        redisUtil.set("name2","从 Redis数据信息");

        // 获取主信息

        redisUtil.chooseMaster();


        log.info(">>> 获取主数据 {}",redisUtil.get("name2").toString());

        redisUtil.chooseSlave();

        log.info(">>> 获取从数据 {}",redisUtil.get("name2").toString());


    }

}

~~~

# RedisUtil 处理

~~~java

@Slf4j
@Component(value = "redisUtil")
public class RedisUtil {


	@Resource(name = "masterRedisTemplate")
	private RedisTemplate<String, Object> masterRedisTemplate;

	@Resource(name = "slaveRedisTemplate")
	private RedisTemplate<String, Object> slaveRedisTemplate;

	@PostConstruct
	public void chooseMaster() {
		redisTemplate = masterRedisTemplate;
	}

	public void chooseSlave() {
		redisTemplate = slaveRedisTemplate;
	}
	private RedisTemplate<String, Object> redisTemplate;

	/** -------------------1.key相关操作--------------------- */
	/******1.1 删除操作开始******/
	/**
	 * 功能描述: 通过key删除key
	 */
	public void delByKey(String key) {
		redisTemplate.delete(key);
	}
    
    // 额外的其它方法
~~~



package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * jasypt 生成
 *
 * @author yuejianli
 * @date 2023-05-17
 */
@Log4j2
public class JasyptTest {

    @Test
    public void showText() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的salt
        textEncryptor.setPassword("123456");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("Zkong_1234");
        String redisPassword = textEncryptor.encrypt("zk123");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("redisPassword:"+redisPassword);

        String originUsername = textEncryptor.decrypt(username);
        String originPassword = textEncryptor.decrypt(password);
        String originRedisPassword = textEncryptor.decrypt(redisPassword);
        System.out.println("originUsername:"+originUsername);
        System.out.println("originPassword:"+originPassword);
        System.out.println("originRedisPassword:"+originRedisPassword);

    }

    @Test
    public void show2Text() {
        PooledPBEStringEncryptor textEncryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("123456");
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        textEncryptor.setConfig(config);


        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("Zkong_1234");
        String redisPassword = textEncryptor.encrypt("zk123");
        System.out.println("username:"+username);
        System.out.println("password:"+password);
        System.out.println("redisPassword:"+redisPassword);

        String originUsername = textEncryptor.decrypt(username);
        String originPassword = textEncryptor.decrypt(password);
        String originRedisPassword = textEncryptor.decrypt(redisPassword);
        System.out.println("originUsername:"+originUsername);
        System.out.println("originPassword:"+originPassword);
        System.out.println("originRedisPassword:"+originRedisPassword);

    }
}

package top.yueshushu.learn;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;

/**
 * @ClassName:YamlApplicationTests
 * @Description TODO
 * @Author 岳建立
 * @Date 2021/4/10 12:02
 * @Version 1.0
 **/
@SpringBootTest
@Log4j2
public class JdbcApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
        log.info("输出数据库jdbcTemplate连接信息,{}",jdbcTemplate);
    }
    @Test
    void metaDataTest() throws Exception {
        //获取 DataSource 对象
        DataSource dataSource=jdbcTemplate.getDataSource();
        //获取 DatabaseMetaData 数据库元数据
        DatabaseMetaData databaseMetaData = dataSource.getConnection().getMetaData();
        log.info("用户名是{}",databaseMetaData.getUserName());
        log.info("url是{}",databaseMetaData.getURL());
        log.info("数据库类型是{}",databaseMetaData.getDatabaseProductName());

    }
}


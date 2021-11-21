package top.yueshushu.learn.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @ClassName:JdbcTemplateConfig
 * @Description JdbcTemplate 的配置信息
 * @Author zk_yjl
 * @Date 2021/9/2 10:45
 * @Version 1.0
 * @Since 1.0
 **/
@Component
public class JdbcTemplateConfig {
    /**
     * 创建数据库SpringBoot 对应的 JdbcTemplate.
     * 用 @Qualifier 注解 指定使用的是哪一个 Datasource
     * @param dataSourceOne
     * @return
     */
    @Bean("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne(@Qualifier("dataSourceOne") DataSource dataSourceOne){
        return new JdbcTemplate(dataSourceOne);
    }
    /**
     * 创建数据库SpringBoot2 对应的 JdbcTemplate.
     * 用 @Qualifier 注解 指定使用的是哪一个 Datasource
     * @param dataSourceTwo
     * @return
     */
    @Bean("jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo(@Qualifier("dataSourceTwo") DataSource dataSourceTwo){
        return new JdbcTemplate(dataSourceTwo);
    }
}

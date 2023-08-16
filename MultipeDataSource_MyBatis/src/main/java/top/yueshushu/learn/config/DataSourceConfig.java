package top.yueshushu.learn.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @ClassName:DataSourceConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/2 11:50
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
public class DataSourceConfig {
    /**
     * Mybatis 多数据源配置 springboot 数据库
     * @return
     */
    @Bean(name="dataSourceOne")
    @ConfigurationProperties("spring.datasource.one")
    @Primary
    public DataSource dataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * Mybatis 多数据源配置 springboot2 数据库
     * @return
     */
    @Bean(name="dataSourceTwo")
    @ConfigurationProperties("spring.datasource.two")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }
}

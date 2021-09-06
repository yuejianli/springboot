package top.yueshushu.learn.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @ClassName:DataSourceConfig
 * @Description 数据库源的配置
 * @Author zk_yjl
 * @Date 2021/9/2 10:42
 * @Version 1.0
 * @Since 1.0
 **/
@Component
public class DataSourceConfig {
    /**
     * 创建 springboot 的数据库的数据源 DataSource
     * @return
     */
    @Bean("dataSourceOne")
    @ConfigurationProperties("spring.datasource.one")
    @Primary
    public DataSource dataSourceOne(){

        return DruidDataSourceBuilder.create().build();
    }
    /**
     * 创建 springboot2 的数据库的数据源 DataSource
     * @return
     */
    @Bean("dataSourceTwo")
    @ConfigurationProperties("spring.datasource.two")
    public DataSource dataSourceTwo(){

        return DruidDataSourceBuilder.create().build();
    }
}

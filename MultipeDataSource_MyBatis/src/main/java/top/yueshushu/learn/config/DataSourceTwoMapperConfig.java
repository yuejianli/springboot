package top.yueshushu.learn.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName:DataSourceTwoMapperConfig
 * @Description TODO
 * @Author zk_yjl
 * @Date 2021/9/2 12:29
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@MapperScan(
        basePackages="top.yueshushu.learn.mapper.mapper2", //配置扫描的mapper接口
        sqlSessionFactoryRef = "sqlSessionFactoryTwo", //使用的Factory
        sqlSessionTemplateRef = "sqlSessionTemplateTwo") //使用的SqlSessionTemplate
public class DataSourceTwoMapperConfig {
    @Resource(name="dataSourceTwo")
    private DataSource dataSourceTwo;
    /**本数据源扫描的mapper路径*/
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/mapper2/**/*.xml";
    /**
     * 创建SqlSessionFactory 对象
     */
    @Bean(name="sqlSessionFactoryTwo")
    //引入参数 MyBatisProperties，用于解决日志失效的问题
    public SqlSessionFactory sqlSessionFactoryTwo(MybatisProperties mybatisProperties){
        try{
            SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSourceTwo);
            //设置mapper配置文件
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
           // 将 mybatis的配置，传递过来。
            sqlSessionFactoryBean.setConfiguration(mybatisProperties.getConfiguration());
            return sqlSessionFactoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 配置事务
     * @param dataSourceTwo
     * @return
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManagerTwo(
            @Qualifier("dataSourceTwo") DataSource dataSourceTwo) {
        return new DataSourceTransactionManager(dataSourceTwo);
    }
    /**
     * 通过SqlSessionFactory 创建 SqlSessionTemplate
     * @return
     */
    //引入参数 MyBatisProperties，用于解决日志失效的问题
    @Bean(name="sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplate(MybatisProperties mybatisProperties){
        return new SqlSessionTemplate(sqlSessionFactoryTwo(mybatisProperties));
    }
}

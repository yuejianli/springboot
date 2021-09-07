package top.yueshushu.learn.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @ClassName:DataSourceOneMapperConfig
 * @Description 数据库1的配置
 * @Author zk_yjl
 * @Date 2021/9/2 11:56
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@MapperScan(
        basePackages = "top.yueshushu.learn.mapper.mapper1",  //配置扫描的mapper接口
        sqlSessionFactoryRef ="sqlSessionFactoryOne", //使用的Factory
        sqlSessionTemplateRef = "sqlSessionTemplateOne") //使用的SqlSessionTemplate
public class DataSourceOneMapperConfig {
    @Resource(name="dataSourceOne")
    private DataSource dataSourceOne;
    /**本数据源扫描的mapper路径*/
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper/mapper1/**/*.xml";
    /**
     * 创建SqlSessionFactory 对象
     */
    @Bean(name="sqlSessionFactoryOne")
    @Primary
    public SqlSessionFactory sqlSessionFactoryOne(){
        try{
            SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();;
            sqlSessionFactoryBean.setDataSource(dataSourceOne);
            //设置mapper配置文件
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
            //将 mybatis的配置，传递过来。
            return sqlSessionFactoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 配置事务
     * @param dataSourceOne
     * @return
     */
    @Bean
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManagerOne(
            @Qualifier("dataSourceOne") DataSource dataSourceOne) {
        return new DataSourceTransactionManager(dataSourceOne);
    }
    /**
     * 通过SqlSessionFactory 创建 SqlSessionTemplate
     * @return
     */
    @Bean(name="sqlSessionTemplateOne")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(){
        return new SqlSessionTemplate(sqlSessionFactoryOne());
    }
}

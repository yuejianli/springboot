package top.yueshushu.learn.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
@MapperScan(basePackages="top.yueshushu.learn.mapper2",
sqlSessionFactoryRef = "sqlSessionFactoryTwo",
sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class DataSourceTwoMapperConfig {
    @Resource(name="dataSourceTwo")
    private DataSource dataSourceTwo;
    /**本数据源扫描的mapper路径*/
    static final String MAPPER_LOCATION = "classpath:mybatis/mapper2/**/*.xml";
    /**
     * 创建SqlSessionFactory 对象
     */
    @Bean(name="sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryTwo(){
        try{
            SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(dataSourceTwo);
            //设置mapper配置文件
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
            return sqlSessionFactoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 通过SqlSessionFactory 创建 SqlSessionTemplate
     * @return
     */
    @Bean(name="sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplate(){

        return new SqlSessionTemplate(sqlSessionFactoryTwo());
    }
}

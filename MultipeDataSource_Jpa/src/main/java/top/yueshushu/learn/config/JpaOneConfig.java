package top.yueshushu.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * @ClassName:JpaOneConfig
 * @Description 数据库 springboot的Jpa 主库 配置信息
 * @Author zk_yjl
 * @Date 2021/9/6 18:00
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "top.yueshushu.learn.repository.one", // 指定扫描仓库的位置
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBeanOne", //指定扫描实体的位置
        transactionManagerRef = "platformTransactionManagerOne") //指定事务
public class JpaOneConfig {
    @Resource(name="dataSourceOne")
    DataSource dataSourceOne;
    @Autowired
    JpaProperties jpaProperties;

    @Primary  //配置默认
    @Bean(name = "entityManagerPrimaryOne")
    public EntityManager entityManagerOne(EntityManagerFactoryBuilder builder) {
        return localContainerEntityManagerFactoryBeanOne(builder).getObject().createEntityManager();
    }

    @Bean
    @Primary //配置默认
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanOne(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSourceOne)
                // 设置实体的包
                .packages("top.yueshushu.learn.pojo.one")
                //设置配置信息
                .properties(jpaProperties.getProperties())
                //设置持久化的名称
                .persistenceUnit("onePersistenceUnit")
                .build();
    }
    @Bean
    @Primary  //配置默认
    PlatformTransactionManager platformTransactionManagerOne(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryBeanOne = localContainerEntityManagerFactoryBeanOne(builder);
        return new JpaTransactionManager(factoryBeanOne.getObject());
    }
}

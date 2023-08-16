package top.yueshushu.learn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * @Description 数据库 springboot的Jpa配置信息
 * @Author zk_yjl
 * @Date 2021/9/6 18:00
 * @Version 1.0
 * @Since 1.0
 **/
@Configuration
@EnableJpaRepositories(basePackages = "top.yueshushu.learn.repository.two", // 指定扫描仓库的位置
        entityManagerFactoryRef ="localContainerEntityManagerFactoryBeanTwo",  //指定扫描实体的位置
        transactionManagerRef = "platformTransactionManagerTwo") //指定事务
@EnableTransactionManagement
public class JpaTwoConfig {

    @Resource(name="dataSourceTwo")
    DataSource dataSourceTwo;
    @Autowired
    JpaProperties jpaProperties;


    @Bean(name = "entityManagerTwo")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return localContainerEntityManagerFactoryBeanTwo(builder).getObject().createEntityManager();
    }

    @Bean
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBeanTwo(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSourceTwo)
                .packages("top.yueshushu.learn.pojo.two")
                .properties(jpaProperties.getProperties())
                //设置持久化的名称
                .persistenceUnit("twoPersistenceUnit")
                .build();
    }
    @Bean
    PlatformTransactionManager platformTransactionManagerTwo(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean factoryBeanTwo = localContainerEntityManagerFactoryBeanTwo(builder);
        return new JpaTransactionManager(factoryBeanTwo.getObject());
    }
}

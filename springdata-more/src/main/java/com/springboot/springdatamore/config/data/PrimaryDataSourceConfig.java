package com.springboot.springdatamore.config.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/29
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = PrimaryDataSourceConfig.REPOSITORY_PACKAGE,
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryDataSourceConfig {

    static final String REPOSITORY_PACKAGE = "com.springboot.springdatamore.repository.primary";
    private static final String ENTITY_PACKAGE = "com.springboot.springdatamore.entity.primary";

    @Bean("primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("primaryJpaProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.jpa.primary")
    public JpaProperties primaryJpaProperties() {
        return new JpaProperties();
    }

    @Bean("primaryHibernateProperties")
    @Primary
    @ConfigurationProperties(prefix = "spring.jpa.primary.hibernate")
    public HibernateProperties primaryHibernateProperties(){
        return new HibernateProperties();
    }

    @Bean("primaryEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primaryDataSource") DataSource dataSource,
            @Qualifier("primaryJpaProperties") JpaProperties jpaProperties,
            @Qualifier("primaryHibernateProperties") HibernateProperties hibernateProperties,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                .packages(ENTITY_PACKAGE)
                .persistenceUnit("primaryPersistenceUnit").build();
    }

    @Bean("primaryTransactionManager")
    @Primary
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * 该方法仅在需要使用JdbcTemplate对象时选用
     *
     * @param dataSource 注入名为primaryDataSource的bean
     * @return 数据源JdbcTemplate对象
     */
/*    @Primary
    @Bean(name = "primaryJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/

}

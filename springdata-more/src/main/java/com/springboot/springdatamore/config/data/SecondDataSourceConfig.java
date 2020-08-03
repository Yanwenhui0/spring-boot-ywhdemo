package com.springboot.springdatamore.config.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
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
        basePackages = SecondDataSourceConfig.REPOSITORY_PACKAGE,
        transactionManagerRef = "secondTransactionManager",
        entityManagerFactoryRef = "secondEntityManagerFactoryBean"
)
public class SecondDataSourceConfig {

    static final String REPOSITORY_PACKAGE = "com.springboot.springdatamore.repository.second";
    private static final String ENTITY_PACKAGE = "com.springboot.springdatamore.entity.second";

    @Bean("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource secondDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("secondJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.second")
    public JpaProperties secondJpaProperties() {
        return new JpaProperties();
    }

    @Bean("secondHibernateProperties")
    @ConfigurationProperties(prefix = "spring.jpa.second.hibernate")
    public HibernateProperties secondHibernateProperties(){
        return new HibernateProperties();
    }

    @Bean("secondEntityManagerFactoryBean")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactoryBean(
            @Qualifier("secondDataSource") DataSource dataSource,
            @Qualifier("secondJpaProperties") JpaProperties jpaProperties,
            @Qualifier("secondHibernateProperties") HibernateProperties hibernateProperties,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                .packages(ENTITY_PACKAGE)
                .persistenceUnit("secondPersistenceUnit").build();
    }

    @Bean("secondTransactionManager")
    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondEntityManagerFactoryBean") EntityManagerFactory entityManagerFactory ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}


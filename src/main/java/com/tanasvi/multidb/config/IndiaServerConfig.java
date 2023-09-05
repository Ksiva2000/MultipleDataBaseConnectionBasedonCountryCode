package com.tanasvi.multidb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
//
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories(entityManagerFactoryRef = "sqlServerEntityManagerFactory",
        transactionManagerRef = "sqlServerTransactionManager",
        basePackages = "com.tanasvi.multidb.india.repository")
public class IndiaServerConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "india.datasource")
    public DataSourceProperties sqlServerDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    public DataSource sqlServerDataSource(@Qualifier("sqlServerDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "sqlServerEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean sqlServerEntityManagerFactory(@Qualifier("sqlServerDataSource") DataSource sqlServerDataSource, EntityManagerFactoryBuilder builder) {

        return builder.dataSource(sqlServerDataSource)
                .packages("com.tanasvi.multidb.entity")
                .persistenceUnit("user")
                .build();

    }

    @Bean
    @Primary
    public PlatformTransactionManager sqlServerTransactionManager(@Qualifier("sqlServerEntityManagerFactory")
                                                                              EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
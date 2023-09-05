package com.tanasvi.multidb.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories(entityManagerFactoryRef = "sql1ServerEntityManagerFactory",
        transactionManagerRef = "sql1ServerTransactionManager",
        basePackages = "com.tanasvi.multidb.uk.repository")
public class UKServerConfig {

    @Bean
    @ConfigurationProperties(prefix = "uk.datasource")
    public DataSourceProperties sql1ServerDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource sql1ServerDataSource(@Qualifier("sql1ServerDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "sql1ServerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean sql1ServerEntityManagerFactory(@Qualifier("sql1ServerDataSource") DataSource sql1ServerDataSource, EntityManagerFactoryBuilder builder) {

        return builder.dataSource(sql1ServerDataSource)
                .packages("com.tanasvi.multidb.entity")
                .persistenceUnit("user")
                .build();

    }

    @Bean
    public PlatformTransactionManager sql1ServerTransactionManager(@Qualifier("sql1ServerEntityManagerFactory")
                                                                              EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
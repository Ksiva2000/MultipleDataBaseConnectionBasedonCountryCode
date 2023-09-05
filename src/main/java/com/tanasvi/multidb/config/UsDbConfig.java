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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableJpaRepositories(entityManagerFactoryRef = "usEntityManagerFactory",
transactionManagerRef = "usTransactionManager",
basePackages = "com.tanasvi.multidb.usa.repository")
public class UsDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "usa.datasource")
    public DataSourceProperties usDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean
    public DataSource usDataSource(@Qualifier("usDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "usEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean usEntityManagerFactory(@Qualifier("usDataSource") DataSource usDataSource, EntityManagerFactoryBuilder builder) {

        return builder.dataSource(usDataSource)
                .packages("com.tanasvi.multidb.entity")
          .persistenceUnit("user")
                .build();

    }

    @Bean
    public PlatformTransactionManager usTransactionManager(@Qualifier("usEntityManagerFactory")
                                                                              EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}

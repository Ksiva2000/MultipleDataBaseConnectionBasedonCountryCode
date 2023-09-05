package com.tanasvi.multidb.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.tanasvi.multidb.europe.repository",
    entityManagerFactoryRef = "europeEntityManagerFactory",
    transactionManagerRef = "europeTransactionManager")
public class EuropeConfig {

    @Bean
    @ConfigurationProperties(prefix = "europe.datasource")
    public DataSourceProperties europeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource europeDataSource(@Qualifier("europeDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean europeEntityManagerFactory(@Qualifier("europeDataSource") DataSource europeDataSource, EntityManagerFactoryBuilder builder) {
        return builder.dataSource(europeDataSource)
                .packages("com.tanasvi.multidb.entity")
                .persistenceUnit("user")
                .build();
    }

    @Bean
    public PlatformTransactionManager europeTransactionManager(@Qualifier("europeEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}

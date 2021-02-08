package org.example.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.example.processor.Processors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.annotation.PreDestroy;
import javax.persistence.Basic;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.example.repository")
public class DataConfig {

    @PreDestroy
    public void destroy(){
        System.out.println("-----------DATA CONFİG DESTROYED----------");
    }


    Environment env;
    public DataConfig(Environment environment) {
        this.env = environment;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] {"org.example.entity"});
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();


        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setInitialSize(10);
        basicDataSource.setInitialSize(5);
        basicDataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
        basicDataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        basicDataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return basicDataSource;
/*
        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
        simpleDriverDataSource.setDriver(new Driver());
        simpleDriverDataSource.setUrl();
        simpleDriverDataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
        simpleDriverDataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        return simpleDriverDataSource;

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("06061989");
        return dataSource;
*/
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }




}

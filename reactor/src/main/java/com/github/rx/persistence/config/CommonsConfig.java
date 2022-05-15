package com.github.rx.persistence.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAsync
@EnableScheduling
@EnableAspectJAutoProxy
@EnableTransactionManagement
//@ComponentScan({ "com.github.rx.*" })
//@PropertySource("classpath:/config.yaml")
public abstract class CommonsConfig implements AsyncConfigurer {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("datasource.driverclass"));
        dataSource.setUrl(environment.getRequiredProperty("datasource.jdbcURL"));
        dataSource.setUsername(environment.getRequiredProperty("datasource.user"));
        dataSource.setPassword(environment.getRequiredProperty("datasource.password"));
        //dataSource.setSchema("PUBLIC");
        jpaVendorAdapter().setDatabasePlatform(environment.getRequiredProperty("datasource.dialect"));
        return dataSource;
    }



    @Bean(name = "transactionManager")
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }



    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setPersistenceXmlLocation("classpath:/persistence.xml");
        entityManagerFactoryBean.setPersistenceUnitName("pu");
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        final Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.naming_strateg", "org.hibernate.cfg.ImprovedNamingStrategy");
        jpaProperties.put("generate-ddl", "true");
        entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
        return entityManagerFactoryBean;
    }


    @Bean(name = "jpaVendorAdapter")
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");//org.hibernate.dialect.HSQLDialect
        return jpaVendorAdapter;
    }

}

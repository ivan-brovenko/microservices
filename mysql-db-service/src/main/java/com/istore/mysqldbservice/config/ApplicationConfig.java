package com.istore.mysqldbservice.config;

import com.istore.mysqldbservice.factory.AbstractRepositoryFactory;
import com.istore.mysqldbservice.factory.Context;
import com.istore.mysqldbservice.factory.impl.JPAFactory;
import com.istore.mysqldbservice.factory.impl.NativeQueryFactory;
import com.istore.mysqldbservice.repository.impl.jpa.UserRepositoryJPAImpl;
import com.istore.mysqldbservice.utils.impl.ConsoleLogManager;
import com.istore.mysqldbservice.utils.impl.FileLogManager;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class ApplicationConfig {

//    @Bean
//    public UserRepositoryJPAImpl userRepository(){
//        ConsoleLogManager consoleLogManager = ConsoleLogManager.getLogManager();
//        FileLogManager fileLogManager = FileLogManager.getLogManager();
//
//        UserRepositoryJPAImpl userRepository = new UserRepositoryJPAImpl();
//        userRepository.add(consoleLogManager);
//        userRepository.add(fileLogManager);
//        return userRepository;
//    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan(new String[]{"com.isore.mysqldbservice.model"});
//
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.setJpaProperties(additional());
//        return em;
//    }

    @Bean
    public Properties additional(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2dll.auto","create-drop");
        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        return properties;
    }

    @Bean
    public Context context(){
        AbstractRepositoryFactory jpaFactory = new JPAFactory();
        AbstractRepositoryFactory jdbcFactory = new NativeQueryFactory();

        Context context = new Context(jdbcFactory);
        return context;
    }

    @Bean
    @Primary
    public DataSource dataSource(){
        return DataSourceBuilder.create()
        .driverClassName("com.mysql.jdbc.Driver")
        .url("jdbc:mysql://localhost:3306/istore_db")
        .username("root")
        .password("rootroot").build();
    }

}

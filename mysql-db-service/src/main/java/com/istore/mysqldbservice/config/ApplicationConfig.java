package com.istore.mysqldbservice.config;

import com.istore.mysqldbservice.factory.AbstractRepositoryFactory;
import com.istore.mysqldbservice.factory.Context;
import com.istore.mysqldbservice.factory.impl.JPAFactory;
import com.istore.mysqldbservice.factory.impl.NativeQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Autowired
    private ApplicationContext applicationContext;

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

//    @Bean
//    public Properties additional(){
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.hbm2dll.auto","create-drop");
//        properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }

    @Bean
    public Context context(){
        AbstractRepositoryFactory jpaFactory = new JPAFactory();
        AbstractRepositoryFactory jdbcFactory = new NativeQueryFactory();
        jpaFactory.setApplicationContext(applicationContext);
        Context context = new Context(jpaFactory);
        return context;
    }

//    @Bean
//    @Primary
//    public DataSource dataSource(){
//        return DataSourceBuilder.create()
//        .driverClassName("com.mysql.jdbc.Driver")
//        .url("jdbc:mysql://localhost:3306/istore_db")
//        .username("root")
//        .password("rootroot").build();
//    }

}

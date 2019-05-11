package com.istore.mysqldbservice.factory.impl;

import com.istore.mysqldbservice.factory.AbstractRepositoryFactory;
import com.istore.mysqldbservice.repository.*;
import com.istore.mysqldbservice.repository.impl.jpa.*;
import com.istore.mysqldbservice.utils.impl.ConsoleLogManager;
import com.istore.mysqldbservice.utils.impl.FileLogManager;
import org.springframework.context.ApplicationContext;

public class JPAFactory implements AbstractRepositoryFactory {
    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public UserRepository getUserRepository() {
        ConsoleLogManager consoleLogManager = ConsoleLogManager.getLogManager();
        FileLogManager fileLogManager = FileLogManager.getLogManager();
        UserRepositoryJPAImpl userRepository = applicationContext.getBean(UserRepositoryJPAImpl.class);
        userRepository.add(consoleLogManager);
        userRepository.add(fileLogManager);
        return userRepository;
    }

    public RoleRepository getRoleRepository() {
        return new RoleRepositoryJPAImpl();
    }

    public ProductRepository getProductRepository() {
        return new ProductRepositoryJPAImpl();
    }

    public OrderRepository getOrderRepository() {
        return new OrderRepositoryJPAImpl();
    }

    public CategoryRepository getCategoryRepository() {
        return new CategoryRepositoryJPAImpl();
    }
}

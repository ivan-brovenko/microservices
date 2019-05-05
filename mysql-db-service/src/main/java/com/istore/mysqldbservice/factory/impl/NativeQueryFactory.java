package com.istore.mysqldbservice.factory.impl;

import com.istore.mysqldbservice.factory.AbstractRepositoryFactory;
import com.istore.mysqldbservice.memento.Storage;
import com.istore.mysqldbservice.repository.CategoryRepository;
import com.istore.mysqldbservice.repository.OrderRepository;
import com.istore.mysqldbservice.repository.ProductRepository;
import com.istore.mysqldbservice.repository.RoleRepository;
import com.istore.mysqldbservice.repository.UserRepository;
import com.istore.mysqldbservice.repository.impl.jdbc.CategoryRepositoryJDBCImpl;
import com.istore.mysqldbservice.repository.impl.jdbc.OrderRepositoryJDBCImpl;
import com.istore.mysqldbservice.repository.impl.jdbc.ProductRepositoryJDBCImpl;
import com.istore.mysqldbservice.repository.impl.jdbc.RoleRepositoryJDBCImpl;
import com.istore.mysqldbservice.repository.impl.jdbc.UserRepositoryJDBCImpl;
import com.istore.mysqldbservice.utils.impl.ConsoleLogManager;
import com.istore.mysqldbservice.utils.impl.FileLogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


public class NativeQueryFactory implements AbstractRepositoryFactory {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public UserRepository getUserRepository() {
        ConsoleLogManager consoleLogManager = ConsoleLogManager.getLogManager();
        FileLogManager fileLogManager = FileLogManager.getLogManager();
        UserRepositoryJDBCImpl userRepository = new UserRepositoryJDBCImpl();
        userRepository.add(consoleLogManager);
        userRepository.add(fileLogManager);
        userRepository.setApplicationContext(applicationContext);

        return userRepository;
    }

    public RoleRepository getRoleRepository() {
        return new RoleRepositoryJDBCImpl();
    }

    public ProductRepository getProductRepository() {
        return new ProductRepositoryJDBCImpl();
    }

    public OrderRepository getOrderRepository() {
        return new OrderRepositoryJDBCImpl();
    }

    public CategoryRepository getCategoryRepository() {
        return new CategoryRepositoryJDBCImpl();
    }

}

package com.istore.mysqldbservice.factory.impl;

import com.istore.mysqldbservice.factory.AbstractRepositoryFactory;
import com.istore.mysqldbservice.repository.*;
import com.istore.mysqldbservice.repository.impl.jdbc.*;
import com.istore.mysqldbservice.repository.impl.jpa.UserRepositoryJPAImpl;
import com.istore.mysqldbservice.utils.impl.ConsoleLogManager;
import com.istore.mysqldbservice.utils.impl.FileLogManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class NativeQueryFactory implements AbstractRepositoryFactory{

    public UserRepository getUserRepository() {
        ConsoleLogManager consoleLogManager = ConsoleLogManager.getLogManager();
        FileLogManager fileLogManager = FileLogManager.getLogManager();
        UserRepositoryJDBCImpl userRepository = new UserRepositoryJDBCImpl();
        userRepository.add(consoleLogManager);
        userRepository.add(fileLogManager);
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

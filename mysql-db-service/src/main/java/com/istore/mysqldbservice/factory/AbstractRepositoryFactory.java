package com.istore.mysqldbservice.factory;


import com.istore.mysqldbservice.repository.*;
import org.springframework.context.ApplicationContext;

public interface AbstractRepositoryFactory {

    void setApplicationContext(ApplicationContext applicationContext);

    UserRepository getUserRepository();

    RoleRepository getRoleRepository();

    ProductRepository getProductRepository();

    OrderRepository getOrderRepository();

    CategoryRepository getCategoryRepository();
}

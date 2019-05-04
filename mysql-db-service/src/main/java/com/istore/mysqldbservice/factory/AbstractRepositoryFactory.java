package com.istore.mysqldbservice.factory;


import com.istore.mysqldbservice.repository.*;

public interface AbstractRepositoryFactory {
    UserRepository getUserRepository();

    RoleRepository getRoleRepository();

    ProductRepository getProductRepository();

    OrderRepository getOrderRepository();

    CategoryRepository getCategoryRepository();
}

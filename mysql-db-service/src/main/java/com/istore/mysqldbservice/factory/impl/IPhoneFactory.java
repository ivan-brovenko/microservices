package com.istore.mysqldbservice.factory.impl;

import com.istore.mysqldbservice.factory.ProductFactory;
import com.istore.mysqldbservice.model.IPhone;
import com.istore.mysqldbservice.model.Product;

public class IPhoneFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        return new IPhone();
    }
}

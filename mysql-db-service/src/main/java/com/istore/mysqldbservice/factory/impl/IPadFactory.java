package com.istore.mysqldbservice.factory.impl;

import com.istore.mysqldbservice.factory.ProductFactory;
import com.istore.mysqldbservice.model.IPad;
import com.istore.mysqldbservice.model.Product;

public class IPadFactory implements ProductFactory {
    @Override
    public Product createProduct() {
        return new IPad();
    }
}

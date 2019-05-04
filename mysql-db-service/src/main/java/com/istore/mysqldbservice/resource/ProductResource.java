package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.Product;
import com.istore.mysqldbservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/products")
public class ProductResource {

    private ProductRepository productRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}

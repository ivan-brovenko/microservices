package com.istore.mongodbservice.resource;

import com.istore.mongodbservice.document.Product;
import com.istore.mongodbservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/products")
public class ProductResource {

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }
}

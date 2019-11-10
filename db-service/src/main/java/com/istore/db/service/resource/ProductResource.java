package com.istore.db.service.resource;

import com.istore.db.service.model.Product;
import com.istore.db.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/products")
public class ProductResource {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public List<Product> addAllProducts(@RequestBody List<Product> products) {
        return productRepository.saveAll(products);
    }
}

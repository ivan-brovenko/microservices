package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.Category;
import com.istore.mysqldbservice.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/categories")
public class ProductCategoryResource {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Category addCategory(Category category) {
        return productCategoryRepository.save(category);
    }
}

package com.istore.mongodbservice.resource;

import com.istore.mongodbservice.document.Category;
import com.istore.mongodbservice.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Category addCategory(@RequestBody Category category) {
        return productCategoryRepository.save(category);
    }
}

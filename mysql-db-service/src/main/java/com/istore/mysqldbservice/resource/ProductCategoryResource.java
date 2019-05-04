package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.Category;
import com.istore.mysqldbservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/categories")
public class ProductCategoryResource {

    private CategoryRepository categoryRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}

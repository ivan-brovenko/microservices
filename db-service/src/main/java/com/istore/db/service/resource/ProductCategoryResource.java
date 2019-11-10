package com.istore.db.service.resource;

import com.istore.db.service.model.Category;
import com.istore.db.service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/categories")
public class ProductCategoryResource {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public List<Category> addAllCategories(@RequestBody List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }
}

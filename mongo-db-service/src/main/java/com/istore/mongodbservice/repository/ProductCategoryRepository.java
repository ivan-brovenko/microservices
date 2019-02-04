package com.istore.mongodbservice.repository;


import com.istore.mongodbservice.document.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends MongoRepository<Category, Integer> {
}

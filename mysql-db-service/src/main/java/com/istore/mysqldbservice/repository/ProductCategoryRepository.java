package com.istore.mysqldbservice.repository;

import com.istore.mysqldbservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<Category, Integer> {
}

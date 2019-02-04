package com.istore.mongodbservice.repository;

import com.istore.mongodbservice.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {
}

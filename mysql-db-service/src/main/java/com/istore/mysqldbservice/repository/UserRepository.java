package com.istore.mysqldbservice.repository;


import com.istore.mysqldbservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    User findUserByEmailAndPassword(String email, String password);

    User save(User user);

    List<User> findAll();
}

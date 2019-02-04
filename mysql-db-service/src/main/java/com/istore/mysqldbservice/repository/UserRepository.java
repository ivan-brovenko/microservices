package com.istore.mysqldbservice.repository;


import com.istore.mysqldbservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailAndPassword(String email, String password);
}

package com.istore.mysqldbservice.repository;


import com.istore.mysqldbservice.memento.Storage;
import com.istore.mysqldbservice.model.User;
import org.bson.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    User findUserByEmailAndPassword(String email, String password);

    User save(User user);

    List<User> findAll();

    void rollback(String userEmail);

    User updateUser(User user);

    void rollbackForward(String userEmail);

    List<User> saveAll(List<User> users);

    void removeAll();

    List<User> getUsersInfoWithoutRoles();

    List<User> findUsersByUsername(String username);

    Document groupBy(String field);

    List<User> getSortedUsersByEmail();

    List<User> getLimitedUsers(String limit);

    List<User> getUsersInfoWithoutRolesNA();

    List<User> findUsersByUsernameNA(String username);

    Document groupByNA(String field);

    List<User> getSortedUsersByEmailNA();

    List<User> getLimitedUsersNA(String limit);

}

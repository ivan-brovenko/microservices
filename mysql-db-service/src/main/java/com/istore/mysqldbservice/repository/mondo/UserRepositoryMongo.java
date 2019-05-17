package com.istore.mysqldbservice.repository.mondo;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("mongo")
public class UserRepositoryMongo implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public User save(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public void rollback(String userEmail) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void rollbackForward(String userEmail) {

    }

    @Override
    public List<User> saveAll(List<User> users) {
        users.forEach(user -> mongoTemplate.save(user));
        return users;
    }
}

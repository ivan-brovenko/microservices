package com.istore.mysqldbservice.repository.impl.jpa;

import com.istore.mysqldbservice.memento.Storage;
import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import com.istore.mysqldbservice.utils.LogManager;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserRepositoryJPAImpl implements UserRepository {


    private List<LogManager> logManagers = new ArrayList<>();

    public void add(LogManager logManager){
        logManagers.add(logManager);
    }

    public void remove(LogManager logManager) {
        logManagers.remove(logManager);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {

        User user =  entityManager()
                .createQuery("SELECT u from User where u.email = :email" +
                        " AND u.password = :password", User.class)
                .getSingleResult();
        logManagers.forEach(logManager -> logManager.log("User:"+user));
        return user;
    }

    @Override
    public User save(User user) {
        logManagers.forEach(logManager -> logManager.log("Start "));

        entityManager().persist(user);
        logManagers.forEach(logManager -> logManager.log("User:"+user.getEmail()+" added"));

        return user;
    }

    @Override
    public List<User> findAll() {

        List<User> users =  (List<User>) entityManager().createQuery("select u from User")
                .getResultStream().collect(Collectors.toList());
        logManagers.forEach(logManager -> logManager.log("Users: "+users));
        return users;
    }

    @Override
    public void rollback(String userEmail) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    private static EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("emf")
                .createEntityManager();
    }
}

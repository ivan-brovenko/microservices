package com.istore.mysqldbservice.repository.impl.jpa;

import com.istore.mysqldbservice.memento.Storage;
import com.istore.mysqldbservice.memento.UserSnapshot;
import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import com.istore.mysqldbservice.utils.LogManager;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserRepositoryJPAImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Storage storage;

    private List<LogManager> logManagers = new ArrayList<>();

    public void add(LogManager logManager) {
        logManagers.add(logManager);
    }

    public void remove(LogManager logManager) {
        logManagers.remove(logManager);
    }

    @Override
    public User findUserByEmailAndPassword(String email, String password) {

        User user = entityManager
                .createQuery("SELECT u from User where u.email = :email" +
                        " AND u.password = :password", User.class)
                .getSingleResult();
        logManagers.forEach(logManager -> logManager.log("User:" + user));
        return user;
    }

    @Override
    public User save(User user) {
        logManagers.forEach(logManager -> logManager.log("Start "));
        entityManager.persist(user);
        logManagers.forEach(logManager -> logManager.log("User:" + user.getEmail() + " added"));
        return user;
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(root);
        TypedQuery<User> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void rollback(String userEmail) {
        User userFromDB = findUserByEmail(userEmail);
        if (Objects.nonNull(userFromDB)) {
            UserSnapshot userSnapshot = storage.getLastSnapshotByUserEmail(userEmail);
            if (Objects.nonNull(userSnapshot)) {
                User user = new User();
                user.setEmail(userEmail);
                user.load(userSnapshot);
                updateUser(user);
            }
        }
    }

    @Override
    public void rollbackForward(String userEmail) {
        User userFromDB = findUserByEmail(userEmail);
        if (Objects.nonNull(userFromDB)) {
            UserSnapshot userSnapshot = storage.forward(userEmail);
            if (Objects.nonNull(userSnapshot)) {
                User user = new User();
                user.setEmail(userEmail);
                user.load(userSnapshot);
                updateUser(user);
            }
        }
    }

    @Override
    public List<User> saveAll(List<User> users) {
        users.forEach(user -> entityManager.persist(user));
        return users;
    }

    @Override
    public void removeAll() {

    }

    @Override
    public List<User> getUsersInfoWithoutRoles() {
        return null;
    }

    @Override
    public List<User> findUsersByUsername(String username) {
        return null;
    }

    @Override
    public Document groupBy(String field) {
        return null;
    }

    @Override
    public List<User> getSortedUsersByEmail() {
        return null;
    }


    @Override
    public List<User> getLimitedUsers(String limit) {
        return null;
    }

    @Override
    public List<User> getUsersInfoWithoutRolesNA() {
        return null;
    }

    @Override
    public List<User> findUsersByUsernameNA(String username) {
        return null;
    }

    @Override
    public Document groupByNA(String field) {
        return null;
    }

    @Override
    public List<User> getSortedUsersByEmailNA() {
        return null;
    }


    @Override
    public List<User> getLimitedUsersNA(String limit) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        User formerUser = findUserByEmail(user.getEmail());
        storage.addSnapshot(formerUser.save(), user.getEmail());
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate update = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> userRoot = update.from(User.class);
        Expression<String> userName = userRoot.get("username");
        Expression<String> password = userRoot.get("password");
        update.set("username", user.getUsername());
        update.set("password", user.getPassword());
        Query queue = entityManager.createQuery(update);
        queue.executeUpdate();
        return null;
    }

    private User findUserByEmail(String email) {
        return findAll().stream().filter(user -> user.getEmail().equals(email))
                .findFirst().get();
    }
}

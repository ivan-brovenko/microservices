package com.istore.mysqldbservice.repository.mondo;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

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

    @Override
    public void removeAll() {
        mongoTemplate.remove(User.class);
    }

    @Override
    public List<User> getUsersInfoWithoutRoles() {
        ProjectionOperation projectToMatchModel = project()
                .andExclude("role");


        Aggregation aggregation
                = newAggregation(projectToMatchModel);
        AggregationResults<User> output
                = mongoTemplate.aggregate(aggregation, "user", User.class);

        return output.getMappedResults();
    }

    @Override
    public List<User> findUsersByUsername(String username) {
        MatchOperation matchStage = Aggregation.match(new Criteria("username").is(username));

        Aggregation aggregation = newAggregation(matchStage);

        AggregationResults<User> output
                = mongoTemplate.aggregate(aggregation, "user", User.class);
        return output.getMappedResults();
    }

    @Override
    public Document groupBy(String field) {
        GroupOperation groupByStateAndSumPop = group(field);
        Aggregation aggregation = newAggregation(groupByStateAndSumPop);
        AggregationResults<User> result =
                mongoTemplate.aggregate(aggregation, "user", User.class);
        return result.getRawResults();

    }

    @Override
    public List<User> getSortedUsers(String field) {
        SortOperation sortByAvgPopAsc = sort(new Sort(Sort.Direction.ASC, field));
        Aggregation aggregation = newAggregation(sortByAvgPopAsc);
        AggregationResults<User> result =
                mongoTemplate.aggregate(aggregation, "user", User.class);
        return result.getMappedResults();
    }

    @Override
    public List<User> getLimitedUsers(String limit) {
        LimitOperation limitToOnlyFirstDoc = limit(1);
        Aggregation aggregation = newAggregation(limitToOnlyFirstDoc);
        AggregationResults<User> result =
                mongoTemplate.aggregate(aggregation, "user", User.class);
        return result.getMappedResults();
    }

    @Override
    public List<User> getLimitedUsersNA(String limit) {
        return findAll().stream().limit(Integer.parseInt(limit))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getUsersInfoWithoutRolesNA() {
        return null;
    }

    @Override
    public List<User> findUsersByUsernameNA(String username) {
        return mongoTemplate.findAll(User.class)
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public Document groupByNA(String field) {
        return null;
    }

    @Override
    public List<User> getSortedUsersNA(String field) {
        return null;
    }


}

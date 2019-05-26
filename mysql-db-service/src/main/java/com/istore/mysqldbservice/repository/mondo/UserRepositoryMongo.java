package com.istore.mysqldbservice.repository.mondo;

import com.istore.mysqldbservice.model.Role;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
@Qualifier("mongo")
public class UserRepositoryMongo implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoCollection mongoCollection;

    @Override
    public User findUserByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public User save(User user) {

        Document document = new Document("email",user.getEmail()).append("password",user.getPassword())
                .append("username",user.getUsername());
        mongoCollection.insertOne(document);
        return user;
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
    public Document groupBy(String emailValue) {
        GroupOperation groupByStateAndSumPop = group("email").count().as("count");
//        MatchOperation filterStates = match(new Criteria("email").regex(Pattern.compile("\\d+@" + emailValue)));
        Aggregation aggregation = newAggregation(groupByStateAndSumPop);
        AggregationResults<User> result =
                mongoTemplate.aggregate(aggregation, "user", User.class);
        return result.getRawResults();

    }

    @Override
    public List<User> getSortedUsersByEmail() {
        SortOperation sortByAvgPopAsc = sort(new Sort(Sort.Direction.ASC, "email"));
        Aggregation aggregation = newAggregation(sortByAvgPopAsc);
        AggregationResults<User> result =
                mongoTemplate.aggregate(aggregation, "user", User.class);
        return result.getMappedResults();
    }

    @Override
    public List<User> getLimitedUsers(String limit) {
        LimitOperation limitToOnlyFirstDoc = limit(Long.parseLong(limit));
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
        return findAll().stream().map(user -> {
            User user1 = new User();
            user1.setEmail(user.getEmail());
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            user1.setPhone(user.getPhone());
            return user1;
        }).collect(Collectors.toList());
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
        Map<String, List<User>> users = findAll().stream()
                .collect(groupingBy(User::getEmail));
        Map<String,Integer> newMap = new HashMap<>();
        users.entrySet().forEach(user-> {
            newMap.put(user.getKey(),user.getValue().size());
        });


        Document document = new Document();
                document.putAll(newMap);

        return document ;
    }

    @Override
    public List<User> getSortedUsersByEmailNA() {
        List<User> all = findAll();
        all.sort(Comparator.comparing(User::getEmail));
        return all;
    }


}

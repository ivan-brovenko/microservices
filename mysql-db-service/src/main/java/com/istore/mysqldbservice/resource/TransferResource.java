package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.impl.jpa.UserRepositoryJPAImpl;
import com.istore.mysqldbservice.repository.mondo.UserRepositoryMongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TransferResource {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @Autowired
    private UserRepositoryJPAImpl userRepositoryJPA;


    @PostMapping("/mongo")
    public void transferMySQLToMongo() {
        List<User> usersFromSQL = userRepositoryJPA.findAll();
        MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase("mongo");
        MongoCollection<Document> collection = database.getCollection("user");
        List<Document> documents = usersFromSQL.stream()
                .map(this::convert)
                .collect(Collectors.toList());
        collection.insertMany(documents);


    }

    @PostMapping("/mysql")
    public void transferMongoToMysql() {
        List<User> usersFromMongo = userRepositoryMongo.findAll();
        userRepositoryJPA.saveAll(usersFromMongo);
    }

    Document convert(User user){
        Document doc = new Document("email", user.getEmail())
                .append("username", user.getUsername())
                .append("password", user.getPassword())
                .append("role", new Document("role", 203).append("id", user.getRole().getId())
                .append("name",user.getRole().getName()));
        return doc;
    }
//
//    UserMongo convert(User userMongo){
//        UserMongo user = new UserMongo();
//        user.setEmail(userMongo.getEmail());
//        user.setRole(userMongo.getRole());
//        user.setUsername(userMongo.getUsername());
//        user.setPassword(userMongo.getPassword());
//        return user;
//    }
}

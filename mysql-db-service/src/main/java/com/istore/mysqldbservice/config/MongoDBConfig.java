package com.istore.mysqldbservice.config;

import com.istore.mysqldbservice.repository.UserRepository;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

    private MongoCollection<Document> collection;

    @Bean
    public MongoCollection<Document> getCollection() {
        return collection;
    }

    @Bean
    public MongoClient mongo() {
        MongoClient mongoClient = new MongoClient("localhost");
        MongoDatabase database = mongoClient.getDatabase("mongo");
        collection = database.getCollection("user");
        return mongoClient;
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "mongo");
    }

    @Bean
    public WriteConcernResolver writeConcernResolver() {
        return action -> {
            System.out.println("Using Write Concern of Acknowledged");
            return WriteConcern.ACKNOWLEDGED;
        };
    }
}

package com.istore.mysqldbservice.config;

import com.istore.mysqldbservice.repository.UserRepository;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteConcernResolver;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    public MongoClient mongo() {
        return new MongoClient("localhost");
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

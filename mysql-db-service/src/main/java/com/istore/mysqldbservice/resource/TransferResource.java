package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.impl.jpa.UserRepositoryJPAImpl;
import com.istore.mysqldbservice.repository.mondo.UserRepositoryMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferResource {

    @Autowired
    private UserRepositoryMongo userRepositoryMongo;

    @Autowired
    private UserRepositoryJPAImpl userRepositoryJPA;


    @PostMapping("/mongo")
    public void transferMySQLToMongo() {
        List<User> usersFromSQL = userRepositoryJPA.findAll();
        userRepositoryMongo.saveAll(usersFromSQL);

    }

    @PostMapping("/mysql")
    public void transferMongoToMysql() {
        List<User> usersFromMongo = userRepositoryMongo.findAll();
        userRepositoryJPA.saveAll(usersFromMongo);
    }
}

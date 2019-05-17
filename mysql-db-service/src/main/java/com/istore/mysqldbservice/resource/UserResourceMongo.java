package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResourceMongo {

    @Autowired
    @Qualifier("mongo")
    private UserRepository userRepository;


    @RequestMapping(value = "/allMongo", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/addMongo", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/updateMongo", method = RequestMethod.POST)
    public void update(@RequestBody User user) {
        userRepository.updateUser(user);
    }

    @RequestMapping(value = "/addAllMongo", method = RequestMethod.POST)
    public List<User> addAllUsers(@RequestBody List<User> users) {
        return userRepository.findAll();
    }
}

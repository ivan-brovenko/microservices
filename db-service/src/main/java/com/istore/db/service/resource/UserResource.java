package com.istore.db.service.resource;

import com.istore.db.service.model.User;
import com.istore.db.service.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/istore/users")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public User login(@RequestBody User user) {
        User resultUser = userRepository.findById(user.getEmail()).get();
        if (resultUser.getPassword().equals(user.getPassword())) {
            return resultUser;
        }
        return null;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody User user) {
        userRepository.save(user);
    }
}

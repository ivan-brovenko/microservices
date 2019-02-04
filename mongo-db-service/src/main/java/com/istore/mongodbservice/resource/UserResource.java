package com.istore.mongodbservice.resource;

import com.istore.mongodbservice.document.User;
import com.istore.mongodbservice.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/users")
@Data
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public User login(@RequestBody User user) {
        return userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return userRepository.save(user);
    }

}

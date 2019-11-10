package com.istore.db.service.resource;

import com.istore.db.service.model.User;
import com.istore.db.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/users")
public class UserResource {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody User user) {
        userRepository.delete(user);
    }
}

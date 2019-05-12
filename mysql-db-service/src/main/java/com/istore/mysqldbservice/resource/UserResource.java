package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.factory.Context;
import com.istore.mysqldbservice.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/istore/users")
@Data
public class UserResource {

    @Autowired
    private Context context;


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json",
            consumes = "application/json")
    public User login(@RequestBody User user) {
        return context.getFactory().getUserRepository().findUserByEmailAndPassword(user.getEmail(), user.getPassword());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return context.getFactory().getUserRepository().findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        return context.getFactory().getUserRepository().save(user);
    }

    @RequestMapping(value = "/rollback/{email}", method = RequestMethod.POST)
    public void rollback(@PathVariable("email") String email) {
        context.getFactory().getUserRepository().rollback(email);
    }

    @RequestMapping(value = "/rollbackForward/{email}", method = RequestMethod.POST)
    public void rollbackForward(@PathVariable("email") String email) {
        context.getFactory().getUserRepository().rollbackForward(email);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody User user) {
        context.getFactory().getUserRepository().updateUser(user);
    }

    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public List<User> addAllUsers(@RequestBody List<User> users) {
        return context.getFactory().getUserRepository().saveAll(users);
    }

}

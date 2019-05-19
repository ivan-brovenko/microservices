package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

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
    public User registerUser(@RequestBody User user) throws InterruptedException {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            Thread.sleep(1000);
            return userRepository.save(user);
        }
    }

    @RequestMapping(value = "/addPlenty/{number}", method = RequestMethod.POST)
    public String addPlenty(@PathVariable("number") String number) {
        IntStream.range(0, Integer.parseInt(number))
                .forEach(e -> {
                    User user = new User();
                    user.setEmail(e + "");
                    userRepository.save(user);
                });
        return "added";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public void delete() {
        userRepository.removeAll();
    }


    @RequestMapping(value = "/updateMongo", method = RequestMethod.POST)
    public void update(@RequestBody User user) {
        userRepository.updateUser(user);
    }

    @RequestMapping(value = "/allMongo", method = RequestMethod.POST)
    public List<User> addAllUsers(@RequestBody List<User> users) {
        return userRepository.findAll();
    }

    ///////////////////////////////////////
    //lab6

    @RequestMapping(value = "/withoutRoles", method = RequestMethod.GET)
    public List<User> getSortedUsers() {
        return userRepository.getUsersInfoWithoutRoles();
    }

    @RequestMapping(value = "/byUsername/{username}", method = RequestMethod.GET)
    public List<User> getUsersBuUserName(@PathVariable("username") String username) {
        return userRepository.findUsersByUsername(username);
    }

    @RequestMapping(value = "/groupBy/{field}")
    public Document groupBy(@PathVariable("field") String field) {
        return userRepository.groupBy(field);
    }

    @RequestMapping(value = "/sorted/{field}")
    public List<User> getSortedUsers(@PathVariable("field") String field) {
        return userRepository.getSortedUsers(field);
    }

    @RequestMapping(value = "/limited/{limit}")
    public List<User> getLimitedUsers(@PathVariable("limit") String limit) {
        return userRepository.getLimitedUsers(limit);
    }


}

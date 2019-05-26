package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.User;
import com.istore.mysqldbservice.repository.UserRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
public class UserResourceMongo {

    @Autowired
    @Qualifier("mongo")
    private UserRepository userRepository;

    private Map<Integer,String> emailMap = new HashMap<>();

    public UserResourceMongo() {
        emailMap.put(0,"@gmail.com");
        emailMap.put(1,"@mail.ru");
        emailMap.put(2,"@urk.net");
        emailMap.put(3,"@yahoo.com");
    }

    @RequestMapping(value = "/allMongo", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/addMongo", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user){
        int count = 0;
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            count++;
            if (count > 3) {
                throw e;
            }
            return userRepository.save(user);
        }
    }

    @RequestMapping(value = "/addPlenty/{number}", method = RequestMethod.POST)
    public String addPlenty(@PathVariable("number") String number) {
        long before = System.currentTimeMillis();
        IntStream.range(0, Integer.parseInt(number))
                .forEach(e -> {
                    User user = new User();
                    int emailId = (int)(Math.random()*3);
                    String email = emailMap.get(emailId);
                    user.setEmail("666"+email);
                    user.setPhone("66666");
                    user.setPassword("password");
                    user.setUsername("66666");
                    registerUser(user);
                });
        return "" + (System.currentTimeMillis() - before);
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
    public List<User> getUsersWithoutRoles() {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.getUsersInfoWithoutRoles();
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/byUsername/{username}", method = RequestMethod.GET)
    public List<User> getUsersBuUserName(@PathVariable("username") String username) {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.findUsersByUsername(username);
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/sorted/email")
    public List<User> getSortedUsers() {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.getSortedUsersByEmail();
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/withoutRolesNA", method = RequestMethod.GET)
    public List<User> getUsersWithoutRolesNA() {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.getUsersInfoWithoutRolesNA();
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/byUsernameNA/{username}", method = RequestMethod.GET)
    public List<User> getUsersBuUserNameNA(@PathVariable("username") String username) {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.findUsersByUsernameNA(username);
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/groupBy/{email}")
    public Document groupBy(@PathVariable("email") String email) {
        long start = System.currentTimeMillis();
        Document document = userRepository.groupBy(email);
        System.out.println(System.currentTimeMillis() - start);
        return document;
    }

    @RequestMapping(value = "/groupByNA/{field}")
    public Document groupByNA(@PathVariable("field") String field) {
        long start = System.currentTimeMillis();
        Document document = userRepository.groupByNA(field);
        System.out.println(System.currentTimeMillis() - start);
        return document;
    }

    @RequestMapping(value = "/sorted/emailNA")
    public List<User> getSortedUsersNA() {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.getSortedUsersByEmail();
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/limitedNA/{limit}")
    public List<User> getLimitedUsersNA(@PathVariable("limit") String limit) {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.getLimitedUsersNA(limit);
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }

    @RequestMapping(value = "/limited/{limit}")
    public List<User> getLimitedUsers(@PathVariable("limit") String limit) {
        long start = System.currentTimeMillis();
        List<User> users = userRepository.getLimitedUsers(limit);
        System.out.println(System.currentTimeMillis() - start);
        return users;
    }
}

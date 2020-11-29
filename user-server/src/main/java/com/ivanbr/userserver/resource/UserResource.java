package com.ivanbr.userserver.resource;

import com.ivanbr.userserver.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User registerUser(@RequestBody User user) {
        return user;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserByName(@PathVariable String username){
        User user = new User();
        user.setId(1L);
        user.setUsername(username);
        user.setPassword("123");
        return user;
    }
}

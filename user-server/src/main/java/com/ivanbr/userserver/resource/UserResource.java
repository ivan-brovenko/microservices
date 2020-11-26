package com.ivanbr.userserver.resource;

import com.ivanbr.userserver.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<User> getUsers(){
        return new ArrayList<>();
    }
}

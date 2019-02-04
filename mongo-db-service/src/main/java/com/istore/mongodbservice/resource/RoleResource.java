package com.istore.mongodbservice.resource;

import com.istore.mongodbservice.document.Role;
import com.istore.mongodbservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/roles")
public class RoleResource {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}

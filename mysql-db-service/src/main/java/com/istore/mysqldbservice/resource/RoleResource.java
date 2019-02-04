package com.istore.mysqldbservice.resource;

import com.istore.mysqldbservice.model.Role;
import com.istore.mysqldbservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}

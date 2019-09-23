package com.istore.db.service.resource;

import com.istore.db.service.model.Role;
import com.istore.db.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/istore/roles")
public class RoleResource {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}

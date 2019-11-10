package com.istore.db.service.resource;

import com.istore.db.service.model.Role;
import com.istore.db.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Role getRoleById(@PathVariable("id") Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Role addRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteRole(@RequestBody Role role) {
        roleRepository.delete(role);
    }
}

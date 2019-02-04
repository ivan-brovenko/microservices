package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "userSet")
@ToString(exclude = "userSet")
public class Role {

    @Id
    private int id;
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> userSet;
}

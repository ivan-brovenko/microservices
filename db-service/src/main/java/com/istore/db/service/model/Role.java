package com.istore.db.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.jackson.JsonComponent;

import javax.persistence.*;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "userSet")
@ToString(exclude = "userSet")
@JsonComponent("role")
public class Role {

    @Id
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> userSet;
}

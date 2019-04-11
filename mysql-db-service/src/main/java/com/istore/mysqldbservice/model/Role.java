package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "userSet")
@ToString(exclude = "userSet")
@NoArgsConstructor
public class Role {

    @Id
    private int id;
    private String name;

    private Role(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
    }

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> userSet;

    public static class Builder {
        private int id;
        private String name;

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Role build(){
            return new Role(this);
        }
    }
}

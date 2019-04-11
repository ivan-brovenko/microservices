package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    private String email;
    private String username;
    private String password;

    private User(Builder builder){
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> orderSet;

    public static class Builder {
        private String email;
        private String username;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder username(String username){
            this.username = username;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}

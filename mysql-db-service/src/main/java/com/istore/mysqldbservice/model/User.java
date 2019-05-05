package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.istore.mysqldbservice.memento.UserSnapshot;
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

    private String phone;

    private User(Builder builder) {
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.phone = builder.phone;
    }

    public UserSnapshot save() {
        UserSnapshot userSnapshot = new UserSnapshot();
        userSnapshot.setPassword(password);
        userSnapshot.setPhone(phone);
        userSnapshot.setUsername(username);
        return userSnapshot;
    }

    public void load(UserSnapshot userSnapshot){
        username = userSnapshot.getUsername();
        password = userSnapshot.getPassword();
        phone = userSnapshot.getPhone();
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
        private String phone;

        public Builder(String email, String username, String password) {
            this.email = email;
            this.username = username;
            this.password = password;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            if (this.password != null) {
                return new User(this);
            }
            throw new RuntimeException();
        }
    }
}

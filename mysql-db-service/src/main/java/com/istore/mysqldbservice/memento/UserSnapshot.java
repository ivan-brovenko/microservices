package com.istore.mysqldbservice.memento;

import lombok.Data;

@Data
public class UserSnapshot {
    private String username;
    private String password;
    private String phone;
}

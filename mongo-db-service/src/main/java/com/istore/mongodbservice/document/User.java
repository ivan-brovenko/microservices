package com.istore.mongodbservice.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    private String email;
    private String username;
    private String password;
    private Role role;
}

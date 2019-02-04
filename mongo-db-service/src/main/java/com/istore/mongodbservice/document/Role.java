package com.istore.mongodbservice.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Role {

    @Id
    private int id;
    private String name;
}

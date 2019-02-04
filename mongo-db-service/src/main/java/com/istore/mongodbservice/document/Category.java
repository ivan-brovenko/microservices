package com.istore.mongodbservice.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Category {

    @Id
    private int id;
    private String name;
}

package com.istore.mongodbservice.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Order {

    @Id
    private int id;

    private User user;

    private Product product;
}

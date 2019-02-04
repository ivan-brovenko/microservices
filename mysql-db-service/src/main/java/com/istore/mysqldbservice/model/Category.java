package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "products")
@ToString(exclude = "products")
@Table(name = "product_category")
public class Category {

    @Id
    private int id;
    private String name;

    @OneToMany
    @JsonIgnore
    private Set<Product> products;
}

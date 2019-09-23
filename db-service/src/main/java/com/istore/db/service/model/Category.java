package com.istore.db.service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "products")
@ToString(exclude = "products")
@Table(name = "category")
public class Category {

    @Id
    private int id;

    private String name;

    @OneToMany
    @JsonIgnore
    private Set<Product> products;
}

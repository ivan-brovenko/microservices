package com.istore.mysqldbservice.model;

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

@Data
@Entity
@EqualsAndHashCode(exclude = "products")
@ToString(exclude = "products")
@Table(name = "category")
@NoArgsConstructor
public class Category {

    @Id
    private int id;
    private String name;

    private Category(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    @OneToMany
    @JsonIgnore
    private Set<Product> products;

    public static class Builder {
        private int id;
        private String name;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}

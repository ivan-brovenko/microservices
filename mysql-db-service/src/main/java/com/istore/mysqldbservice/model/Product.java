package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    private int id;
    private String name;
    private double price;

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Order> orders;

    public static class Builder {
        private int id;
        private String name;
        private double price;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder price(double price){
            this.price = price;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }

}

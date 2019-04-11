package com.istore.mysqldbservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_product")
@JsonIgnoreProperties(value = {"user", "product"})
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@NoArgsConstructor
public class Order {

    @Id
    private int id;

    private Order(Builder builder) {
        this.id = builder.id;
    }

    @ManyToOne
    @JoinColumn(name = "user_email", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public static class Builder {
        private int id;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}

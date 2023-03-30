package com.company.data_rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class InputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date expire_date;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Double amount;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Input input;

    public InputProduct(Date expire_date, Double price, Double amount, Product products, Input input) {
        this.expire_date = expire_date;
        this.price = price;
        this.amount = amount;
        this.product = products;
        this.input = input;
    }
}

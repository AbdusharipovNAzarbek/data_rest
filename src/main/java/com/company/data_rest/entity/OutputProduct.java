package com.company.data_rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class OutputProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Double price;
    @Column (nullable = false)
    private Double amount;
    @ManyToOne
    private Product product;
    @ManyToOne
    private Output output;

    public OutputProduct(Double price, Double amount, Product product, Output output) {
        this.price = price;
        this.amount = amount;
        this.product = product;
        this.output = output;
    }
}

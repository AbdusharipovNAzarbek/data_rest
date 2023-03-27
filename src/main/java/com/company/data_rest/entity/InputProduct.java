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
    private Product products;
    @ManyToOne
    private Input input;
}

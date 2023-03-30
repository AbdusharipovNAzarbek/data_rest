package com.company.data_rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Timestamp date = Timestamp.valueOf(LocalDateTime.now());
    private String facture_number;
    @Column(unique = true, nullable = false)
    private Integer code;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Currency currency;

    public Input(String facture_number, Integer code, Supplier supplier, Warehouse warehouse, Currency currency) {
        this.facture_number = facture_number;
        this.code = code;
        this.supplier = supplier;
        this.warehouse = warehouse;
        this.currency = currency;
    }
}

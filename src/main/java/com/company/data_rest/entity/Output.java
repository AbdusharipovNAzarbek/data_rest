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
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date date;
    private String facture_number;
    @Column(unique = true, nullable = false)
    private Integer code;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Currency currency;

    public Output(Date date, String facture_number, Integer code, Client client, Warehouse warehouse, Currency currency) {
        this.date = date;
        this.facture_number = facture_number;
        this.code = code;
        this.client = client;
        this.warehouse = warehouse;
        this.currency = currency;
    }
}

package com.company.data_rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(optional = false)
    private Category category;
    @ManyToOne(optional = false)
    private Attachment attachment;
    @ManyToOne(optional = false)
    private Measure measure;
    @Column(unique = true)
    private String code;
    private boolean active = true;
}

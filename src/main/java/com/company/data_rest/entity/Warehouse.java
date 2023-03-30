package com.company.data_rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean active;
    @ManyToMany
    private List<User> users;

    public Warehouse(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }
}

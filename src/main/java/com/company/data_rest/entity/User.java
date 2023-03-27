package com.company.data_rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String first_name;
    private String last_name;
    @Column(unique = true,nullable = false)
    private String phone_number;
    @Column(unique = true)
    private Integer code;
    @Column(nullable = false)
    private String password;
    private boolean active = true;
}

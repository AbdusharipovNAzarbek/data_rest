package com.company.data_rest.repository;

import com.company.data_rest.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputsRepository extends JpaRepository<Input, Integer> {
    boolean existsByFacture_number(String facture_number);
}

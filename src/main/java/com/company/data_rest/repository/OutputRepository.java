package com.company.data_rest.repository;

import com.company.data_rest.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutputRepository extends JpaRepository<Output,Integer> {
    default boolean existsByFacture_number(String facture_number) {
        return false;
    }
}

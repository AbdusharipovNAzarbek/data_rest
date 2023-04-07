package com.company.data_rest.repository;

import com.company.data_rest.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InputProductsRepository extends JpaRepository<InputProduct,Integer> {
}

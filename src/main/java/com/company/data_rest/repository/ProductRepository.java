package com.company.data_rest.repository;

import com.company.data_rest.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByNameAndCategory_Id(String name, Integer category_id);
}

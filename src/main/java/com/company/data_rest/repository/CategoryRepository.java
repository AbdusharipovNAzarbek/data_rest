package com.company.data_rest.repository;

import com.company.data_rest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}

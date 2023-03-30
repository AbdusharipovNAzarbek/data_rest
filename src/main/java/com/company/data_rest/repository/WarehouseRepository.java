package com.company.data_rest.repository;

import com.company.data_rest.entity.User;
import com.company.data_rest.entity.Warehouse;
import org.springframework.core.metrics.StartupStep;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.HTML;
import java.lang.ref.WeakReference;
import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    boolean existsByName(String name);

    List<Warehouse> findWarehouseByUsersOrderById(List<User> users);
}

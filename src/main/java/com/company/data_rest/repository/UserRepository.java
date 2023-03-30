package com.company.data_rest.repository;

import com.company.data_rest.entity.User;
import com.company.data_rest.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "user", excerptProjection = UserProjection.class)
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findUserByWarehousesId(Integer warehouses_id);
}

package com.company.data_rest.repository;

import com.company.data_rest.entity.Client;
import com.company.data_rest.projection.ClientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "client",excerptProjection = ClientProjection.class)
public interface ClientRepository extends JpaRepository<Client,Integer> {
}

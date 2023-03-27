package com.company.data_rest.repository;

import com.company.data_rest.entity.Measure;
import com.company.data_rest.projection.MeasureProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "measure",excerptProjection = MeasureProjection.class)
public interface MeasureRepository extends JpaRepository<Measure, Integer> {
}

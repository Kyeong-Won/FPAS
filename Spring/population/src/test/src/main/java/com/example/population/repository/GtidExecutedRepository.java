package com.example.population.repository;

import com.example.population.domain.GtidExecuted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GtidExecutedRepository extends JpaRepository<GtidExecuted, Long>, JpaSpecificationExecutor<GtidExecuted> {

}
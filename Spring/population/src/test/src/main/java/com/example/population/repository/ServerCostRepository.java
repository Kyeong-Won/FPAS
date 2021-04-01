package com.example.population.repository;

import com.example.population.domain.ServerCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServerCostRepository extends JpaRepository<ServerCost, String>, JpaSpecificationExecutor<ServerCost> {

}
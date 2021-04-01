package com.example.population.repository;

import com.example.population.domain.ColumnsPriv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ColumnsPrivRepository extends JpaRepository<ColumnsPriv, String>, JpaSpecificationExecutor<ColumnsPriv> {

}
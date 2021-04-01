package com.example.population.repository;

import com.example.population.domain.Proc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProcRepository extends JpaRepository<Proc, String>, JpaSpecificationExecutor<Proc> {

}
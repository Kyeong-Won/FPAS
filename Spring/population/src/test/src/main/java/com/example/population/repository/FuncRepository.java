package com.example.population.repository;

import com.example.population.domain.Func;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FuncRepository extends JpaRepository<Func, String>, JpaSpecificationExecutor<Func> {

}
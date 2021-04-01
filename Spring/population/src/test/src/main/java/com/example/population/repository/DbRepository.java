package com.example.population.repository;

import com.example.population.domain.Db;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DbRepository extends JpaRepository<Db, String>, JpaSpecificationExecutor<Db> {

}
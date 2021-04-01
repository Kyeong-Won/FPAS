package com.example.population.repository;

import com.example.population.domain.TablesPriv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TablesPrivRepository extends JpaRepository<TablesPriv, String>, JpaSpecificationExecutor<TablesPriv> {

}
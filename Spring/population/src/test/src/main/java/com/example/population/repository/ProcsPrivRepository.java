package com.example.population.repository;

import com.example.population.domain.ProcsPriv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProcsPrivRepository extends JpaRepository<ProcsPriv, String>, JpaSpecificationExecutor<ProcsPriv> {

}
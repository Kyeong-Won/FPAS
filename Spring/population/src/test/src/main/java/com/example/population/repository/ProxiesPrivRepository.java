package com.example.population.repository;

import com.example.population.domain.ProxiesPriv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProxiesPrivRepository extends JpaRepository<ProxiesPriv, String>, JpaSpecificationExecutor<ProxiesPriv> {

}
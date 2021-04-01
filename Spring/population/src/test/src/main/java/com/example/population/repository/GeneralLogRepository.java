package com.example.population.repository;

import com.example.population.domain.GeneralLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GeneralLogRepository extends JpaRepository<GeneralLog, Void>, JpaSpecificationExecutor<GeneralLog> {

}
package com.example.population.repository;

import com.example.population.domain.SlowLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SlowLogRepository extends JpaRepository<SlowLog, Void>, JpaSpecificationExecutor<SlowLog> {

}
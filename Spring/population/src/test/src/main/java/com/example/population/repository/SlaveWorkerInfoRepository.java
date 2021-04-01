package com.example.population.repository;

import com.example.population.domain.SlaveWorkerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SlaveWorkerInfoRepository extends JpaRepository<SlaveWorkerInfo, Long>, JpaSpecificationExecutor<SlaveWorkerInfo> {

}
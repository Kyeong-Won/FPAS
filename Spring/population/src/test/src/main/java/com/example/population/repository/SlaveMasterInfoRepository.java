package com.example.population.repository;

import com.example.population.domain.SlaveMasterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SlaveMasterInfoRepository extends JpaRepository<SlaveMasterInfo, String>, JpaSpecificationExecutor<SlaveMasterInfo> {

}
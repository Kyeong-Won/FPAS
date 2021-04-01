package com.example.population.repository;

import com.example.population.domain.SlaveRelayLogInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SlaveRelayLogInfoRepository extends JpaRepository<SlaveRelayLogInfo, String>, JpaSpecificationExecutor<SlaveRelayLogInfo> {

}
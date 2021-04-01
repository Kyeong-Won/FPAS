package com.example.population.repository;

import com.example.population.domain.NdbBinlogIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NdbBinlogIndexRepository extends JpaRepository<NdbBinlogIndex, Long>, JpaSpecificationExecutor<NdbBinlogIndex> {

}
package com.example.population.repository;

import com.example.population.domain.InnodbIndexStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InnodbIndexStatsRepository extends JpaRepository<InnodbIndexStats, String>, JpaSpecificationExecutor<InnodbIndexStats> {

}
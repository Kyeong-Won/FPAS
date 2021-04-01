package com.example.population.repository;

import com.example.population.domain.InnodbTableStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InnodbTableStatsRepository extends JpaRepository<InnodbTableStats, String>, JpaSpecificationExecutor<InnodbTableStats> {

}
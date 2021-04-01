package com.example.population.repository;

import com.example.population.domain.TimeZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeZoneRepository extends JpaRepository<TimeZone, Long>, JpaSpecificationExecutor<TimeZone> {

}
package com.example.population.repository;

import com.example.population.domain.TimeZoneName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeZoneNameRepository extends JpaRepository<TimeZoneName, String>, JpaSpecificationExecutor<TimeZoneName> {

}
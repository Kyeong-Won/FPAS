package com.example.population.repository;

import com.example.population.domain.TimeZoneTransitionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeZoneTransitionTypeRepository extends JpaRepository<TimeZoneTransitionType, Long>, JpaSpecificationExecutor<TimeZoneTransitionType> {

}
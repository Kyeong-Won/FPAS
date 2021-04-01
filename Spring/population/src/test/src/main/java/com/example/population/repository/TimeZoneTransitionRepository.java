package com.example.population.repository;

import com.example.population.domain.TimeZoneTransition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeZoneTransitionRepository extends JpaRepository<TimeZoneTransition, Long>, JpaSpecificationExecutor<TimeZoneTransition> {

}
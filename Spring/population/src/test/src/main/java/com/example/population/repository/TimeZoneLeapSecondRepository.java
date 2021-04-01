package com.example.population.repository;

import com.example.population.domain.TimeZoneLeapSecond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TimeZoneLeapSecondRepository extends JpaRepository<TimeZoneLeapSecond, Long>, JpaSpecificationExecutor<TimeZoneLeapSecond> {

}
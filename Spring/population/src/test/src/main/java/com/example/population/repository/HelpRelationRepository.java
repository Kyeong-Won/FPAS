package com.example.population.repository;

import com.example.population.domain.HelpRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HelpRelationRepository extends JpaRepository<HelpRelation, Long>, JpaSpecificationExecutor<HelpRelation> {

}
package com.example.population.repository;

import com.example.population.domain.HelpKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HelpKeywordRepository extends JpaRepository<HelpKeyword, Long>, JpaSpecificationExecutor<HelpKeyword> {

}